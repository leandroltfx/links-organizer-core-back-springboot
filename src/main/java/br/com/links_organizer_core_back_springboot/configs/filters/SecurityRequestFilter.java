package br.com.links_organizer_core_back_springboot.configs.filters;

import br.com.links_organizer_core_back_springboot.dtos.ErrorDTO;
import br.com.links_organizer_core_back_springboot.providers.JWTProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JWTProvider jwtProvider;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header != null) {
            var token = this.jwtProvider.validateToken(header);

            if (token == null) {
                ErrorDTO errorDTO = new ErrorDTO("Token inválido");

                // Converte para JSON
                ObjectMapper mapper = new ObjectMapper();
                String body = mapper.writeValueAsString(errorDTO);

                // Define resposta
                response.setStatus(HttpStatus.FORBIDDEN.value());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.getWriter().write(body);
                response.getWriter().flush();
                return;
            }

            request.setAttribute("user_id", token.getSubject());
        }

        filterChain.doFilter(request, response);
    }
}
