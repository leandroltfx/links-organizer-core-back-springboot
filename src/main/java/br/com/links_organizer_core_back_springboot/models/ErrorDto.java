package br.com.links_organizer_core_back_springboot.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ErrorDto {

    private String message;
    private List<ErrorFieldDto> details = new ArrayList<>();

    public ErrorDto(
            String message
    ) {
        this.message = message;
    }

}
