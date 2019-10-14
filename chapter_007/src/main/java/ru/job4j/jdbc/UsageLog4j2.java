package ru.job4j.jdbc;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * UsageLog4j2
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class UsageLog4j2 {

    private static final Logger LOG = LogManager.getLogger(UsageLog4j2.class.getName());

    public static void main(String[] args) {
        //System.out.println("wer");
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
    }
}
