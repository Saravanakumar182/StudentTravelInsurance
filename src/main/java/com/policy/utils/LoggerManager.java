package com.policy.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.concurrent.ConcurrentHashMap;

public final class LoggerManager {
    // Cache loggers per class to avoid re-creating them (thread-safe)
    private static final ConcurrentHashMap<String, Logger> LOGGER_CACHE = new ConcurrentHashMap<>();
    // Prevent instantiation
    private LoggerManager() {
        throw new UnsupportedOperationException("LoggerManager is a utility class and cannot be instantiated");
    }
    /**
     * Get a logger for a specific class (recommended approach).
     */
    public static Logger getLogger(Class<?> clazz) {
        return LOGGER_CACHE.computeIfAbsent(clazz.getName(), LogManager::getLogger);
    }
    /**
     * Get a logger by name (useful for step definitions or custom modules).
     */
    public static Logger getLogger(String name) {
        return LOGGER_CACHE.computeIfAbsent(name, LogManager::getLogger);
    }

    // ---------- Convenience wrapper methods ----------

    public static void info(Class<?> clazz, String message) {
        getLogger(clazz).info(message);
    }

    public static void debug(Class<?> clazz, String message) {
        getLogger(clazz).debug(message);
    }

    public static void warn(Class<?> clazz, String message) {
        getLogger(clazz).warn(message);
    }

    public static void error(Class<?> clazz, String message) {
        getLogger(clazz).error(message);
    }

    public static void error(Class<?> clazz, String message, Throwable throwable) {
        getLogger(clazz).error(message, throwable);
    }

    public static void fatal(Class<?> clazz, String message, Throwable throwable) {
        getLogger(clazz).fatal(message, throwable);
    }

    // ---------- Framework-specific helpers ----------

    /**
     * Log the start of a test case with a decorative banner.
     */
    public static void logTestStart(Class<?> clazz, String testName) {
        Logger logger = getLogger(clazz);
        logger.info("========================================================");
        logger.info(">>>> STARTING TEST: {}", testName);
        logger.info("========================================================");
    }

    /**
     * Log the end of a test case with status.
     */
    public static void logTestEnd(Class<?> clazz, String testName, String status) {
        Logger logger = getLogger(clazz);
        logger.info("--------------------------------------------------------");
        logger.info("<<<< TEST FINISHED: {} | STATUS: {}", testName, status);
        logger.info("--------------------------------------------------------");
    }

    /**
     * Log a Selenium action (click, type, select) for readable reports.
     */
    public static void logAction(Class<?> clazz, String action, String element) {
        getLogger(clazz).info("ACTION: [{}] on element -> {}", action, element);
    }
    /**
     * Log a verification/assertion step.
     */
    public static void logVerification(Class<?> clazz, String description, boolean passed) {
        Logger logger = getLogger(clazz);
        if (passed) {
            logger.info("✔ VERIFIED: {}", description);
        } else {
            logger.error("✘ FAILED VERIFICATION: {}", description);
        }
    }
}