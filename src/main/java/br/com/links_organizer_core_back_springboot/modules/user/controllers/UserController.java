package br.com.links_organizer_core_back_springboot.modules.user.controllers;

import br.com.links_organizer_core_back_springboot.modules.user.dtos.UserRegistrationRequestDTO;
import br.com.links_organizer_core_back_springboot.modules.user.dtos.UserRegistrationResponseDTO;
import br.com.links_organizer_core_back_springboot.modules.user.mappers.UserMapper;
import br.com.links_organizer_core_back_springboot.modules.user.useCases.CreateUserUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private CreateUserUseCase createUserUseCase;

    @Autowired
    private UserMapper userMapper;

    @PostMapping
    public ResponseEntity<UserRegistrationResponseDTO> registerUser(
            @Valid @RequestBody UserRegistrationRequestDTO userRegistrationRequestDto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                this.createUserUseCase.execute(
                        this.userMapper.toEntity(userRegistrationRequestDto)
                )
        );
    }

}
