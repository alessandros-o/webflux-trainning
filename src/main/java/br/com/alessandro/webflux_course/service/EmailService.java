package br.com.alessandro.webflux_course.service;

import br.com.alessandro.webflux_course.entity.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class EmailService {

    public void confirmaCadastro(User user) {
        log.info("Cadastro confirmado: usuário: {}, ID: {}", user.getName(), user.getId());
    }
}
