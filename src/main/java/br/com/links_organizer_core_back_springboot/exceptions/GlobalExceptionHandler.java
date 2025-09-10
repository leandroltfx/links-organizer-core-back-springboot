package br.com.links_organizer_core_back_springboot.exceptions;

import br.com.links_organizer_core_back_springboot.models.ErrorDto;
import br.com.links_organizer_core_back_springboot.models.ErrorFieldDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> methodArgumentNotValidExceptionHandler(
            MethodArgumentNotValidException exception
    ) {

        ErrorDto errorDto = new ErrorDto("Campos informados inválidos.");

        exception.getBindingResult().getFieldErrors().forEach(err -> {
            ErrorFieldDto errorFieldDto = new ErrorFieldDto(err.getField(), err.getDefaultMessage());
            errorDto.getDetails().add(errorFieldDto);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }

}
