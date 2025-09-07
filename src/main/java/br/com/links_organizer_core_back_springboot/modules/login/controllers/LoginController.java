package br.com.links_organizer_core_back_springboot.modules.login.controllers;

import br.com.links_organizer_core_back_springboot.modules.login.model.dto.LoginRequestDto;
import br.com.links_organizer_core_back_springboot.modules.login.model.dto.LoginResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @PostMapping
    public ResponseEntity<Object> login(
            @RequestBody LoginRequestDto loginRequestDto
    ) {
        LoginResponseDto loginResponseDto = LoginResponseDto.builder()
                .message("Login realizado com sucesso!")
                .token("token")
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(loginResponseDto);
    }

}
