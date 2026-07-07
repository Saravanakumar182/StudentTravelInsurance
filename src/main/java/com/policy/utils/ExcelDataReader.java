package com.policy.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Reads test data from TestData.xlsx (one sheet per module).
 *
 *   Sheet: Travel | Health | Car
 *   Columns: Key | Value | Description
 *
 * Loaded once, cached in memory (thread-safe).
 *
 *   Usage:
 *      String country = ExcelDataReader.get("Travel", "destination.country");
 *      int age        = ExcelDataReader.getInt("Travel", "traveller1.age");
 */
public final class ExcelDataReader {

    private static final String EXCEL_PATH = "src/main/resources/testdata/InsuranceApplication_TestData.xlsx";

    // Cache: sheetName -> (key -> value)
    private static final Map<String, Map<String, String>> DATA_CACHE = new ConcurrentHashMap<>();

    private ExcelDataReader() {}

    /**
     * Get raw string value for a key in a given module sheet.
     */
    public static String get(String sheetName, String key) {
        Map<String, String> sheetData = DATA_CACHE.computeIfAbsent(sheetName,ExcelDataReader::loadSheet);
        String value = sheetData.get(key);
        if (value == null) {
            throw new RuntimeException("Key '" + key + "' not found in sheet '" + sheetName + "'");
        }
        return value;
    }

    public static int getInt(String sheetName, String key) {
        return Integer.parseInt(get(sheetName, key).trim());
    }

    public static double getDouble(String sheetName, String key) {
        return Double.parseDouble(get(sheetName, key).trim());
    }

    // ---- Loader ----
    private static Map<String, String> loadSheet(String sheetName) {
        Map<String, String> map = new HashMap<>();
        File file = new File(EXCEL_PATH);
        if (!file.exists()) {
            throw new RuntimeException("InsuranceApplication_TestData.xlsx not found at: " + EXCEL_PATH);
        }

        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new RuntimeException("Sheet '" + sheetName + "' not found in Excel file");
            }

            DataFormatter formatter = new DataFormatter();  // preserves "01" and dates
            boolean firstRow = true;

            for (Row row : sheet) {
                if (firstRow) { firstRow = false; continue; }   // skip header
                if (row == null) continue;

                Cell keyCell   = row.getCell(0);
                Cell valueCell = row.getCell(1);
                if (keyCell == null) continue;

                String key   = formatter.formatCellValue(keyCell).trim();
                String value = valueCell == null ? "" : formatter.formatCellValue(valueCell).trim();

                if (!key.isEmpty()) map.put(key, value);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load sheet '" + sheetName + "'", e);
        }
        return map;
    }
}