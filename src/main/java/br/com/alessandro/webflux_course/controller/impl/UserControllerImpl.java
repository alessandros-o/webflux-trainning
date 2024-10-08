package br.com.alessandro.webflux_course.controller.impl;

import br.com.alessandro.webflux_course.controller.UserController;
import br.com.alessandro.webflux_course.mapper.UserMapper;
import br.com.alessandro.webflux_course.model.request.UserRequest;
import br.com.alessandro.webflux_course.model.response.UserResponse;
import br.com.alessandro.webflux_course.service.EmailService;
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
    private final UserMapper mapper;
    private final EmailService emailService;

    @Override
    public ResponseEntity<Mono<Void>> save(UserRequest request) {
        var result = service.save(request)
                .doOnNext(emailService::confirmaCadastro)
                .then();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(result);
    }

    @Override
    public ResponseEntity<Mono<UserResponse>> findById(String id) {

        return ResponseEntity.ok().body(
                service.findById(id).map(mapper::toResponse)
        );
    }

    @Override
    public ResponseEntity<Flux<UserResponse>> findAll() {

        return ResponseEntity.ok().body(
                service.findAll().map(mapper::toResponse));
    }

    @Override
    public ResponseEntity<Mono<UserResponse>> update(String id, UserRequest request) {

        return ResponseEntity.ok().body(
                service.update(id, request)
                        .map(mapper::toResponse)
        );
    }

    @Override
    public ResponseEntity<Mono<Void>> delete(String id) {
        return ResponseEntity.ok().body(service.findAndRemove(id));
    }
}
