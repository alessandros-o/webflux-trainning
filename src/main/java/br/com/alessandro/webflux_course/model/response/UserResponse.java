package br.com.alessandro.webflux_course.model.response;

public record UserResponse(
        String id,
        String name,
        String email,
        String password
) {
}
