package ru.job4j.exam.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * StartParser
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class StartParser {

    private Config parserConfig = new Config();
    private String cronExpression = parserConfig.get("cron.time");

    private final static Logger LOGGER = LogManager.getLogger(StoreSQL.class);

    public void doParsing() throws SchedulerException {
        JobDetail job = JobBuilder.newJob(JobSQL.class).build();
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("CronTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                .build();
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
        scheduler.scheduleJob(job, trigger);
    }

    public static void main(String[] args) {
        StartParser startParser = new StartParser();
        try {
            startParser.doParsing();
        } catch (SchedulerException e) {
            LOGGER.error("ERROR", e);
        }
    }
}
