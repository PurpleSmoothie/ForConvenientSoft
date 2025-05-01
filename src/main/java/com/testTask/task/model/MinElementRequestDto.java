package com.testTask.task.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MinElementRequestDto {

    /**
     * Путь к Excel файлу, содержащему числа.
     */
    @NotBlank(message = "Путь к файлу обязателен")
    private String path;

    /**
     * Параметр N для поиска N-го минимального элемента.
     */
    @Schema(
            description = "Порядковый номер минимального числа (начинается с 0). Пример: 0 → самое маленькое, 1 → второе минимальное и т.д.",
            example = "2"
    )
    @Min(value = 1, message = "N должен быть >= 1")
    private int n;
}