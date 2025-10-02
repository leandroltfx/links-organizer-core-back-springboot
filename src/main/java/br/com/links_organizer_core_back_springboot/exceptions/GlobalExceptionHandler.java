package br.com.links_organizer_core_back_springboot.exceptions;

import br.com.links_organizer_core_back_springboot.dtos.ErrorDTO;
import br.com.links_organizer_core_back_springboot.dtos.ErrorFieldDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.naming.AuthenticationException;
import java.net.ConnectException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> methodArgumentNotValidExceptionHandler(
            MethodArgumentNotValidException exception
    ) {

        ErrorDTO errorDTO = new ErrorDTO("Dados inválidos.");

        exception.getBindingResult().getFieldErrors().forEach(err -> {
            ErrorFieldDTO errorFieldDto = new ErrorFieldDTO(err.getField(), err.getDefaultMessage());
            errorDTO.getDetails().add(errorFieldDto);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
    }

    @ExceptionHandler(UserFoundException.class)
    public ResponseEntity<ErrorDTO> userFoundExceptionHandler(
            UserFoundException userFoundException
    ) {
        ErrorDTO errorDTO = new ErrorDTO(userFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorDTO);
    }

    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<ErrorDTO> connectExceptionHandler() {
        ErrorDTO errorDTO = new ErrorDTO("Ocorreu um erro interno, tente novamente.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDTO);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorDTO> authenticationExceptionHandler(
            AuthenticationException authenticationException
    ) {
        ErrorDTO errorDTO = new ErrorDTO(authenticationException.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorDTO);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDTO> userNotFoundException(
            UserNotFoundException userNotFoundException
    ) {
        ErrorDTO errorDTO = new ErrorDTO(userNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorDTO);
    }

}
