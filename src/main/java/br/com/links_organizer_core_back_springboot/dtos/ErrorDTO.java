package br.com.links_organizer_core_back_springboot.dtos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ErrorDTO {

    private String message;
    private List<ErrorFieldDTO> details = new ArrayList<>();

    public ErrorDTO(
            String message
    ) {
        this.message = message;
    }

}