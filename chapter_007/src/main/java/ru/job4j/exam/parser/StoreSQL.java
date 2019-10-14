package ru.job4j.exam.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * StoreSQL
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class StoreSQL implements Closeable {
    private static final Logger LOGGER = LogManager.getLogger(StoreSQL.class.getName());

    private final Config config = new Config();
    private Connection connection;

    public StoreSQL(Connection connection) {
        this.connection = connection;
        this.init();
    }

    private void init() {
        try (PreparedStatement prepStatement =
                     this.connection.prepareStatement(
                             "create table if not exists vacancies (id serial primary key not null, vacancy_name varchar(250),"
                                     +
                                     " description text, actual_date timestamp, link text);")
        ) {
            prepStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }


  /*  public boolean init() {
        try (InputStream in = StoreSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
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
    }*/

    public Vacancy add(Vacancy vacancy) {
        try (PreparedStatement prepStatement = connection.prepareStatement("insert into vacancies(vacancy_name, description, actual_date, link) values (?, ?, ?, ?);")) {
            if (checkForNoDuplicate(vacancy)) {
                prepStatement.setString(1, vacancy.getVacancyName());
                prepStatement.setString(2, vacancy.getDescription());
                prepStatement.setTimestamp(3, Timestamp.valueOf(vacancy.getActualDate()));
                prepStatement.setString(4, vacancy.getLink());
                prepStatement.executeUpdate();
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return vacancy;
    }

    public boolean checkForNoDuplicate(Vacancy vacancy) {
        String vacancyName = vacancy.getVacancyName();
        try (PreparedStatement Checker = this.connection
                .prepareStatement("SELECT * FROM vacancies WHERE vacancy_name = ?")) {
            Checker.setString(1, vacancyName);
            ResultSet rs = Checker.executeQuery();
            if (!rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return false;
    }

    public List<Vacancy> getAll() {
        List<Vacancy> result = new ArrayList<>();
        try (PreparedStatement prepStatement = this.connection.prepareStatement("select * from vacancies;")) {
            ResultSet rs = prepStatement.executeQuery();
            while (rs.next()) {
                result.add(new Vacancy(
                                rs.getString("vacancy_name"),
                                rs.getString("description"),
                                rs.getTimestamp("actual_date").toLocalDateTime(),
                                rs.getString("link")
                        )
                );
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    public void addListOfVacancies(List<Vacancy> vacancies) {
        String sql = "INSERT INTO vacancies(vacancy_name, description, actual_date, link) values (?,?,?,?);";
        try (PreparedStatement prepStatement = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            for (Vacancy vacancy : vacancies) {
                prepStatement.setString(1, vacancy.getVacancyName());
                prepStatement.setString(2, vacancy.getDescription());
                prepStatement.setTimestamp(3, Timestamp.valueOf(vacancy.getActualDate()));
                prepStatement.setString(4, vacancy.getLink());
                prepStatement.addBatch();
            }
            prepStatement.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        if (this.connection != null) {
            try {
                this.connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
