package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        short b = 21;
        long c = 2L;
        double d = 12.2D;
        byte f = 1;
        float g = 33f;
        char h = 'a';
        boolean e = true;
        int a = 1;
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
        LOG.info(String.valueOf(a));
        LOG.info(String.valueOf(b));
        LOG.info(String.valueOf(c));
        LOG.info(String.valueOf(d));
        LOG.info(String.valueOf(e));
        LOG.info(String.valueOf(f));
        LOG.info(String.valueOf(g));
        LOG.info(String.valueOf(h));
    }
}