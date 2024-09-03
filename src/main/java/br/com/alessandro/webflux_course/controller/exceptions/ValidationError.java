package br.com.alessandro.webflux_course.controller.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidationError extends StandardError implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    List<FieldError> fieldErrors = new ArrayList<>();

    public ValidationError(LocalDateTime timeStamp, String path, Integer status, String error, String message) {
        super(timeStamp, path, status, error, message);
    }

    public void addMessage(String fieldName, String message) {
        FieldError error = new FieldError(fieldName, message);
        this.fieldErrors.add(error);
    }

    @Getter
    @AllArgsConstructor
    private static final class FieldError{
        private String fieldName;
        private String message;
    }
}
