package br.com.links_organizer_core_back_springboot.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorFieldDto {

    private String field;
    private String message;

}
