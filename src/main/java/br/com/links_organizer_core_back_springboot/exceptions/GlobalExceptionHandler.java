package br.com.links_organizer_core_back_springboot.exceptions;

import br.com.links_organizer_core_back_springboot.models.ErrorDto;
import br.com.links_organizer_core_back_springboot.models.ErrorFieldDto;
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
    public ResponseEntity<ErrorDto> methodArgumentNotValidExceptionHandler(
            MethodArgumentNotValidException exception
    ) {

        ErrorDto errorDto = new ErrorDto("Dados inválidos.");

        exception.getBindingResult().getFieldErrors().forEach(err -> {
            ErrorFieldDto errorFieldDto = new ErrorFieldDto(err.getField(), err.getDefaultMessage());
            errorDto.getDetails().add(errorFieldDto);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }

    @ExceptionHandler(UserFoundException.class)
    public ResponseEntity<ErrorDto> userFoundExceptionHandler(
        UserFoundException userFoundException
    ) {
        ErrorDto errorDto = new ErrorDto(userFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorDto);
    }

    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<ErrorDto> connectExceptionHandler() {
        ErrorDto errorDto = new ErrorDto("Ocorreu um erro interno, tente novamente.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDto);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorDto> authenticationExceptionHandler(
            AuthenticationException authenticationException
    ) {
        ErrorDto errorDto = new ErrorDto(authenticationException.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorDto);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDto> userNotFoundException(
            UserNotFoundException userNotFoundException
    ) {
        ErrorDto errorDto = new ErrorDto(userNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorDto);
    }

}
