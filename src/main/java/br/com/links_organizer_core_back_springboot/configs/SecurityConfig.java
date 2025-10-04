package br.com.links_organizer_core_back_springboot.configs;

import br.com.links_organizer_core_back_springboot.configs.filters.SecurityRequestFilter;
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
                            .requestMatchers("/collections").permitAll()
                            .requestMatchers(SWAGGER_LIST).permitAll();
                })
                .addFilterBefore(securityRequestFilter, BasicAuthenticationFilter.class);
        ;
        return httpSecurity.build();
    }

}
