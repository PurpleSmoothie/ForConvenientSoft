package com.testTask.task.utils;

import com.testTask.task.exceptions.ExelFileReadingException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class XlsxParser {

    /**
     *  Извлекает целые числа из Excel-файла.
     *
     * @param filePath путь к Excel файлу.
     * @return  список целых чисел из файла.
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
            throw new ExelFileReadingException("Ошибка чтения Excel-файла: " + e.getMessage());
        }

        return result;
    }

    /**
     * Эффективно находит N-й минимальный элемент в списке целых чисел.
     *
     * @param data список целых чисел.
     * @param n номер минимального элемента, который нужно найти.
     * @return N-й минимальный элемент.
     * @throws IllegalArgumentException если n не в допустимом диапазоне .
     */
    public static int findNthSmallest(List<Integer> data, int n) {
        if (n <= 0 || n > data.size()) {
            throw new IllegalArgumentException("N должно быть в пределах от 1 до размера списка");
        }
        return quickSelect(new ArrayList<>(data), 0, data.size() - 1, n - 1);
    }

    private static int quickSelect(List<Integer> nums, int left, int right, int k) {
        while (left <= right) {
            int pivotIndex = partition(nums, left, right);
            if (pivotIndex == k) {
                return nums.get(pivotIndex);
            } else if (pivotIndex < k) {
                left = pivotIndex + 1;
            } else {
                right = pivotIndex - 1;
            }
        }
        throw new RuntimeException("Не удалось найти элемент");
    }

     private static int partition(List<Integer> nums, int left, int right) {
        int pivot = nums.get(right);
        int i = left;
        for (int j = left; j < right; j++) {
            if (nums.get(j) < pivot) {
                Collections.swap(nums, i, j) ;
                i++;
            }
        }
        Collections.swap(nums, i, right);
        return i;
    }
}