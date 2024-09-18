package br.com.alessandro.webflux_course.service;

import br.com.alessandro.webflux_course.entity.User;
import br.com.alessandro.webflux_course.mapper.UserMapper;
import br.com.alessandro.webflux_course.model.request.UserRequest;
import br.com.alessandro.webflux_course.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository repository;
    @Mock
    private UserMapper mapper;

    @InjectMocks
    private UserService service;

    @Test
    void save() {
        UserRequest request = new UserRequest("Alessandro", "teste@teste.com", "123");
        User user = User.builder().build();

        when(mapper.toEntity(any())).thenReturn(user);
        when(repository.save(any())).thenReturn(Mono.just(user));

        var result = service.save(request);

        StepVerifier
                .create(result)
                .expectNextMatches(Objects::nonNull)
                .expectComplete()
                .verify();

        verify(repository, times(1)).save(any());
    }
}