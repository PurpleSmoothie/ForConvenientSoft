package com.testTask.task.utils;

import com.testTask.task.exceptions.ExelFileReadingException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class XlsxParser {

    /**
     * Извлекает целые числа из Excel-файла.
     *
     * @param filePath путь к Excel файлу.
     * @return список целых чисел из файла.
     * @throws ExelFileReadingException если ошибка чтения файла.
     */
    public static List<Integer> extractIntegersFromExcel(String filePath) {
        List<Integer> result = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                for (Cell cell : row) {
                    if (cell.getCellType() == CellType.NUMERIC) {
                        double value = cell.getNumericCellValue();
                        if (value == Math.floor(value)) { // Только целые
                            result.add((int) value);
                        }
                    }
                }
            }

        } catch (IOException e) {
            throw new ExelFileReadingException();
        }

        return result;
    }

    /**
     * Находит N-й минимальный элемент в списке целых чисел.
     *
     * @param data список целых чисел.
     * @param n номер минимального элемента, который нужно найти.
     * @return N-й минимальный элемент.
     * @throws IllegalArgumentException если n не в допустимом диапазоне.
     */
    public static int findNthSmallest(List<Integer> data, int n) {
        if (n <= 0 || n > data.size()) {
            throw new IllegalArgumentException("N должно быть в пределах от 1 до размера списка");
        }

        List<Integer> copyData = new ArrayList<>(data);
        for (int i = 0; i < n - 1; i++) {
            int min = Integer.MAX_VALUE;
            int minIndex = -1;

            for (int j = 0; j < copyData.size(); j++) {
                if (copyData.get(j) < min) {
                    min = copyData.get(j);
                    minIndex = j;
                }
            }

            copyData.remove(minIndex);
        }

        return Collections.min(copyData);
    }
}