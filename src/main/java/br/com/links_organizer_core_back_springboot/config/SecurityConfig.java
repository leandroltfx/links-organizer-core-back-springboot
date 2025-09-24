package br.com.links_organizer_core_back_springboot.config;

import br.com.links_organizer_core_back_springboot.config.filters.SecurityRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private SecurityRequestFilter securityRequestFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // Desabilitando as configurações padrão do Spring Security
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    // Rotas públicas
                    auth
                            .requestMatchers("/login").permitAll()
                            .requestMatchers("/user").permitAll()
                            .requestMatchers("/collections").permitAll();
                })
                .addFilterBefore(securityRequestFilter, BasicAuthenticationFilter.class);
        return httpSecurity.build();
    }

}
