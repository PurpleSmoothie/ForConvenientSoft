package com.testTask.task.exceptions;

public class ExelFileReadingException extends RuntimeException {
  public ExelFileReadingException() {
    super("Ошибка чтения Excel-файла");
  }
}