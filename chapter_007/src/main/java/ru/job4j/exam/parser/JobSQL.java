package ru.job4j.exam.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * JobSQL
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class JobSQL implements Job {
    private static final Logger LOGGER = LogManager.getLogger(StartParser.class);
    private Config parserConfig = new Config();
    private ParserSQL sqlRuParser = new ParserSQL(this.connectSqlDatabase());
    private boolean checker = false;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        this.LOGGER.info("Parsing process started");
        this.sqlRuParser.doNextParsing();
        this.LOGGER.info("Parsing process stopped");
    }

    public Connection connectSqlDatabase() {
        try {
            return DriverManager.getConnection(
                    this.parserConfig.get("url"),
                    this.parserConfig.get("username"),
                    this.parserConfig.get("password")
            );
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
