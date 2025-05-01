package com.testTask.task.controllers;

import com.testTask.task.model.MinElementRequestDto;
import com.testTask.task.services.FilterFileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/path")
public class ReceivePathController {

    private final FilterFileService filterFileService;

    /**
     * Эндпоинт для получения N-го минимального элемента из Excel-файла.
     *
     * @param dto запрос с путем к файлу и значением N.
     * @return N-й минимальный элемент из файла.
     */
    @Operation(summary = "Получить N-е минимальное число из файла")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешно найден минимальный элемент",
                    content = @Content(
                            schema = @Schema(example = "5")
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Некорректные данные запроса",
                    content = @Content(schema = @Schema())
            )
    })
    @PostMapping("/min-element")
    public ResponseEntity<Integer> findNumber(@Valid @RequestBody @Parameter(description = "DTO с путем к файлу и значением N") MinElementRequestDto dto) {
        int result = filterFileService.getNthSmallestElement(dto);
        return ResponseEntity.ok(result);
    }
}