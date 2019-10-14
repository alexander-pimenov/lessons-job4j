package ru.job4j.jdbc.store;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * StoreSQL
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class StoreSQL implements AutoCloseable {
    private final Config config;
    private Connection connect;

    public StoreSQL(Config config) {
        this.config = config;
        this.config.init();
    }

    public boolean init() {
        try {
            this.connect = DriverManager.getConnection(config.get("urlSQLLite"));
            if (this.connect != null) {
                this.connect.setAutoCommit(false);
                String dropIfExists = "DROP TABLE if EXISTS entry;";
                String createIfNotExists = "CREATE TABLE if NOT EXISTS entry (field integer);";
                try (Statement statement = this.connect.createStatement()) {
                    statement.execute(dropIfExists);
                    statement.execute(createIfNotExists);
                    this.connect.commit();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (SQLException e) {
            try {
                this.connect.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return this.connect != null;
    }

    public void generate(int size) {
        String query = "INSERT INTO entry (field) VALUES (?);";
        try (PreparedStatement preparedStatement = this.connect.prepareStatement(query)) {
            for (int i = 1; i <= size; i++) {
                preparedStatement.setInt(1, i);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            this.connect.commit();
        } catch (SQLException e) {
            try {
                this.connect.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public List<Entry> load() {
        List<Entry> result = new ArrayList<>();
        try (PreparedStatement preparedStatement  = this.connect.prepareStatement("SELECT field FROM entry;")) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                result.add(new Entry(rs.getInt("field")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void close() throws Exception {
        if (connect != null) {
            connect.close();
        }
    }
}
