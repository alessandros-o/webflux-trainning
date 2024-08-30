package br.com.alessandro.webflux_course.model.request;

public record UserRequest(
        String name,
        String email,
        String password
) {
}
