package br.com.alessandro.webflux_course.service;

import br.com.alessandro.webflux_course.entity.User;
import br.com.alessandro.webflux_course.mapper.UserMapper;
import br.com.alessandro.webflux_course.model.request.UserRequest;
import br.com.alessandro.webflux_course.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

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
        return repository.findById(id);
    }
}
