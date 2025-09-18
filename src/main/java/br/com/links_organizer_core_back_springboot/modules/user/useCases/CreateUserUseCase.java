package br.com.links_organizer_core_back_springboot.modules.user.useCases;

import br.com.links_organizer_core_back_springboot.exceptions.UserFoundException;
import br.com.links_organizer_core_back_springboot.modules.user.entities.UserEntity;
import br.com.links_organizer_core_back_springboot.modules.user.model.dto.UserRegistrationResponseDto;
import br.com.links_organizer_core_back_springboot.modules.user.repositories.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateUserUseCase {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserRegistrationResponseDto execute(UserEntity userEntity) {
        this.userRepository.findByUserNameOrEmail(
                userEntity.getUserName(),
                userEntity.getEmail()
        ).ifPresent(user -> {
            throw new UserFoundException("Este e-mail e/ou nome de usuário já está em uso.");
        });

        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

        var user = this.userRepository.save(userEntity);

        Dotenv dotenv = Dotenv.load();
        String secret = dotenv.get("JWT_SECRET");

        Algorithm algorithm = Algorithm.HMAC256(secret);
        var token = JWT
                .create()
                .withIssuer("linksorganizer")
                .withSubject(user.getId().toString())
                .sign(algorithm);

        return UserRegistrationResponseDto
                .builder()
                .token(token)
                .message("Usuário cadastrado com sucesso!")
                .build();
    }

}
