package br.com.alessandro.webflux_course.controller.exceptions;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(DuplicateKeyException.class)
    ResponseEntity<Mono<StandardError>> duplicatedKeyException(DuplicateKeyException ex, ServerHttpRequest request) {

        return ResponseEntity.badRequest()
                .body(Mono.just(
                        StandardError.builder()
                                .timeStamp(LocalDateTime.now())
                                .path(request.getPath().toString())
                                .status(HttpStatus.BAD_REQUEST.value())
                                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                                .message(verifyDupKey(ex.getMessage()))
                                .build()
                ));

    }

    @ExceptionHandler(WebExchangeBindException.class)
    ResponseEntity<Mono<ValidationError>> webExchangeBindException(WebExchangeBindException ex, ServerHttpRequest request){
        ValidationError error = new ValidationError(
                LocalDateTime.now(), request.getPath().toString(), HttpStatus.BAD_REQUEST.value(), "Validation Error", "Erro on validation attributes"
        );

        for (FieldError x: ex.getBindingResult().getFieldErrors()) {
            error.addMessage(x.getField(), x.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Mono.just(error));
    }

    private String verifyDupKey(String message) {
        if (message.contains("email dup key")) {
            return "E-mail already registered";
        }
        return "Dup Key Exception";
    }
}
