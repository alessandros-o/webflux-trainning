package br.com.alessandro.webflux_course.controller.impl;

import br.com.alessandro.webflux_course.controller.UserController;
import br.com.alessandro.webflux_course.model.request.UserRequest;
import br.com.alessandro.webflux_course.model.response.UserResponse;
import br.com.alessandro.webflux_course.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService service;

    @Override
    public ResponseEntity<Mono<Void>> save(UserRequest request) {
        var result = service.save(request).then();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(result);
    }

    @Override
    public ResponseEntity<Mono<UserResponse>> find(String id) {
        return null;
    }

    @Override
    public ResponseEntity<Flux<UserResponse>> findAll() {
        return null;
    }

    @Override
    public ResponseEntity<Mono<UserResponse>> update(String id, UserRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Mono<Void>> delete(String id) {
        return null;
    }
}
