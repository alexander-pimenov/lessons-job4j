package ru.job4j.exam.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.jdbc.ConnectionRollback;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

/**
 * StoreSQLTest
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class StoreSQLTest {
    private static final Logger LOG = LogManager.getLogger(StoreSQLTest.class);
    private LocalDateTime actualDate = LocalDateTime.of(2019, Month.JUNE, 3, 0, 0);
    private Vacancy javaDeveloper;

    public Connection init() {
        try (InputStream in = StoreSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Before
    public void beforeTest() {
        this.javaDeveloper = new Vacancy("Java", "java development", this.actualDate, "www.sql.ru");
    }

    @Test
    public void addVacancy() {
        try (StoreSQL storeSQL = new StoreSQL(ConnectionRollback.create(init()))) {
            Vacancy result = storeSQL.add(this.javaDeveloper);
            assertThat(result, is(javaDeveloper));
        } catch (SQLException e) {
            this.LOG.error(e.getMessage(), e);
        }
    }

    @Test
    public void getAll() {
        try (StoreSQL storeSQL = new StoreSQL(ConnectionRollback.create(init()))) {
            List<Vacancy> result = storeSQL.getAll();
            assertThat(result, is(notNullValue()));
        } catch (SQLException e) {
            this.LOG.error(e.getMessage(), e);
        }
    }

}