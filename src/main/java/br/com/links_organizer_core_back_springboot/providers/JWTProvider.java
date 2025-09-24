package br.com.links_organizer_core_back_springboot.providers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Service;

@Service
public class JWTProvider {

    public DecodedJWT validateToken(String token) {
        token = token.replace("Bearer ", "");

        Dotenv dotenv = Dotenv.load();
        String secret = dotenv.get("JWT_SECRET");

        Algorithm algorithm = Algorithm.HMAC256(secret);

        try {
            var tokenDecoded = JWT
                    .require(algorithm)
                    .build()
                    .verify(token);

            return tokenDecoded;
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            return null;
        }
    }

}
