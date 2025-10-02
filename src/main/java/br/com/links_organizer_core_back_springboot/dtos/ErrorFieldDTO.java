package br.com.links_organizer_core_back_springboot.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorFieldDTO {

    private String field;
    private String message;

}
