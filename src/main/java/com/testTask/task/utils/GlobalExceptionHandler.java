package com.testTask.task.utils;

import com.testTask.task.exceptions.ExelFileReadingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Обрабатывает ошибки чтения Excel-файлов.
     *
     * @param e ошибка, выброшенная при чтении файла.
     * @return ResponseEntity с кодом 500 и сообщением об ошибке.
     */
    @ExceptionHandler(ExelFileReadingException.class)
    public ResponseEntity<String> handleExelFileReadingException(ExelFileReadingException e) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Невозможно прочитать Excel-файл. Проверьте его формат или доступность.");
    };


    /**
     * Обрабатывает все остальные исключения.
     *
     * @param e любое другое исключение.
     * @return ResponseEntity с кодом 500 и сообщением об ошибке.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Произошла непредвиденная ошибка: " + e.getMessage());
    }
}
