package br.com.alessandro.webflux_course.service;

import br.com.alessandro.webflux_course.entity.User;
import br.com.alessandro.webflux_course.mapper.UserMapper;
import br.com.alessandro.webflux_course.model.request.UserRequest;
import br.com.alessandro.webflux_course.repository.UserRepository;
import br.com.alessandro.webflux_course.service.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public Mono<User> save(final UserRequest request) {

        var user = mapper.toEntity(request);
        return repository.save(user);
    }

    public Mono<User> findById(final String id) {
        return handleNotFound(repository.findById(id), id);
    }

    public Flux<User> findAll() {
        return repository.findAll();
    }

    public Mono<User> update(String id, UserRequest request) {
        return findById(id)
                .map(entity -> mapper.toEntity(request, entity))
                .flatMap(repository::save);
    }

    public Mono<Void> findAndRemove(String id) {
        return handleNotFound(repository.findAndRemove(id), id).then();
    }

    private <T> Mono<T> handleNotFound(Mono<T> mono, String id) {
        return mono.switchIfEmpty(Mono.error(
                new ObjectNotFoundException(
                        format("Object not found. Id: %s, Type: %s", id, User.class.getSimpleName())
                )));
    }
}
