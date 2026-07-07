package com.policy.utils;

import com.aventstack.extentreports.ExtentTest;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

/**
 * Thread-safe holder for ExtentTest objects (one per running test).
 * Uses ThreadLocal via ConcurrentHashMap keyed on thread ID — parallel-safe.
 */
public final class ExtentTestManager {

    private static final Map<Long, ExtentTest> testMap = new ConcurrentHashMap<>();

    private ExtentTestManager() {}

    public static ExtentTest getTest() {
        return testMap.get(Thread.currentThread().getId());
    }

    public static void setTest(ExtentTest test) {
        testMap.put(Thread.currentThread().getId(), test);
    }

    public static void removeTest() {
        testMap.remove(Thread.currentThread().getId());
    }
}