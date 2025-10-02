package br.com.links_organizer_core_back_springboot.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private static final String[] SWAGGER_LIST = {
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-resource/**",
    };

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // Desabilitando as configurações padrão do Spring Security
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    // Rotas públicas
                    auth
                            .requestMatchers("/user").permitAll()
                            .requestMatchers("/login").permitAll()
                            .requestMatchers(SWAGGER_LIST).permitAll();
                });
        return httpSecurity.build();
    }

}
