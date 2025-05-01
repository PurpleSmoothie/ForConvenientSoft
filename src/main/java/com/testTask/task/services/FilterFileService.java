package com.testTask.task.services;

import com.testTask.task.model.MinElementRequestDto;
import com.testTask.task.utils.XlsxParser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class FilterFileService {

    /**
     * Получает N-й минимальный элемент из Excel-файла по данным DTO.
     *
     * @param dto объект, содержащий путь к файлу и значение N.
     * @return N-й минимальный элемент из файла.
     */
    public int getNthSmallestElement(MinElementRequestDto dto) {
        List<Integer> numbers = XlsxParser.extractIntegersFromExcel(dto.getPath());
        return XlsxParser.findNthSmallest(numbers, dto.getN());
    }
}