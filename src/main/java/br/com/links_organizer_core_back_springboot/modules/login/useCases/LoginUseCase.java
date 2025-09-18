package br.com.links_organizer_core_back_springboot.modules.login.useCases;

import br.com.links_organizer_core_back_springboot.exceptions.UserNotFoundException;
import br.com.links_organizer_core_back_springboot.modules.login.model.dto.LoginRequestDto;
import br.com.links_organizer_core_back_springboot.modules.login.model.dto.LoginResponseDto;
import br.com.links_organizer_core_back_springboot.modules.user.repositories.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
public class LoginUseCase {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public LoginResponseDto execute(
            LoginRequestDto loginRequestDto
    ) throws AuthenticationException {

        var user = this.userRepository
                .findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new UserNotFoundException("E-mail e/ou senha inválidos"));

        var passwordMatches = this.passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword());

        if (!passwordMatches) {
            throw new AuthenticationException("E-mail e/ou senha inválidos");
        }

        Dotenv dotenv = Dotenv.load();
        String secret = dotenv.get("JWT_SECRET");

        Algorithm algorithm = Algorithm.HMAC256(secret);
        var token = JWT
                .create()
                .withIssuer("linksorganizer")
                .withSubject(user.getId().toString())
                .sign(algorithm);

        return LoginResponseDto.builder()
                .message("Login realizado com sucesso!")
                .token(token)
                .build();
    }

}
