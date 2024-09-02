package br.com.alessandro.webflux_course.controller.exceptions;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
public class StandardError implements Serializable {

    @Serial
    private static final long serialVersionUUID = 1L;


    private LocalDateTime timeStamp;
    private String path;
    private Integer status;
    private String error;
    private String message;
}
