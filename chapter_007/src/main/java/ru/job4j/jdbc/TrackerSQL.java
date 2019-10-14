package ru.job4j.jdbc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.tracker.ITracker;
import ru.job4j.tracker.Item;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * TrackerSQL
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class TrackerSQL implements ITracker, AutoCloseable {
    private static final Logger LOGGER = LogManager.getLogger(TrackerSQL.class.getName());

    private Connection connection;

    public TrackerSQL(Connection connection) {
        this.connection = connection;
    }

    public TrackerSQL() {
        this.init();
    }

    public void setTableItems() {
        try (PreparedStatement prepStatement =
                     this.connection.prepareStatement(
                             "create table if not exists items (id serial primary key not null, item_name varchar(250),"
                                     +
                                     " description text, created timestamp, comments text);")
        ) {
            prepStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public boolean init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            assert in != null;
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            this.connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
            this.setTableItems();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return this.connection != null;
    }

    @Override
    public void close() throws Exception {
        if (this.connection != null) {
            this.connection.close();
        }
    }

    @Override
    public Item add(Item item) {
        try (PreparedStatement prepStatement = connection.prepareStatement("insert into items(item_name, description, created, comments) values (?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS)) {
            prepStatement.setString(1, item.getName());
            prepStatement.setString(2, item.getDescription());
            prepStatement.setTimestamp(3, new Timestamp(item.getCreated()));
            prepStatement.setString(4, item.getComments());
            prepStatement.executeUpdate();
            ResultSet generatedKeys = prepStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                item.setId(String.valueOf(generatedKeys.getInt(1)));
                System.out.println(generatedKeys.getInt(1));
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return item;
    }

    @Override
    public boolean replace(String id, Item item) {
        var result = false;
        try (PreparedStatement prepStatement = connection.prepareStatement("update items set item_name = ?, description = ?, created = ?, comments = ? where id = ?;")) {
            prepStatement.setString(1, item.getName());
            prepStatement.setString(2, item.getDescription());
            prepStatement.setTimestamp(3, new Timestamp(item.getCreated()));
            prepStatement.setString(4, item.getComments());
            prepStatement.setInt(5, Integer.parseInt(id.trim()));
            if (prepStatement.executeUpdate() > 0) {
                result = true;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        var result = false;
        try (PreparedStatement prepStatement = connection.prepareStatement("delete from items where id = ?;")) {
            prepStatement.setInt(1, Integer.parseInt(id.trim()));
            if (prepStatement.executeUpdate() > 0) {
                result = true;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public List<Item> findAll() {
        List<Item> result = new ArrayList<>();
        try (PreparedStatement prepStatement = this.connection.prepareStatement("select * from items;")) {
            ResultSet rs = prepStatement.executeQuery();
            while (rs.next()) {
                result.add(new Item(
                                rs.getString("item_name"),
                                rs.getString("description"),
                                rs.getString("id")
                        )
                );
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> result = new ArrayList<>();
        try (PreparedStatement prepStatement = this.connection.prepareStatement("select * from items where item_name = ?")) {
            prepStatement.setString(1, key);
            ResultSet rs = prepStatement.executeQuery();
            while (rs.next()) {
                result.add(new Item(
                                rs.getString("item_name"),
                                rs.getString("description"),
                                rs.getString("id")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Item findById(String id) {
        Item item = null;
        try (PreparedStatement prepStatement = this.connection.prepareStatement("select * from items where id = ?;")) {
            prepStatement.setInt(1, Integer.parseInt(id.trim()));
            ResultSet rs = prepStatement.executeQuery();
            while (rs.next()) {
                item = new Item(
                        rs.getString("item_name"),
                        rs.getString("description"),
                        rs.getInt("id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }
}
