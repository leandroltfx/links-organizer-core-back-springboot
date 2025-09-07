package br.com.links_organizer_core_back_springboot.modules.user.controllers;

import br.com.links_organizer_core_back_springboot.modules.user.model.dto.UserRegistrationRequestDto;
import br.com.links_organizer_core_back_springboot.modules.user.model.dto.UserRegistrationResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping
    public ResponseEntity<UserRegistrationResponseDto> registerUser(
            @RequestBody UserRegistrationRequestDto userRegistrationRequestDto
    ) {
        UserRegistrationResponseDto userRegistrationResponseDto = UserRegistrationResponseDto.builder()
                .message("Usuário cadastrado com sucesso!")
                .token("token")
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(userRegistrationResponseDto);
    }

}
