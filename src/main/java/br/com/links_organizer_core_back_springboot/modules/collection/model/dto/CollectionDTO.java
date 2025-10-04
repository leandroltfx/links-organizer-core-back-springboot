package br.com.links_organizer_core_back_springboot.modules.collection.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CollectionDTO {

    private UUID id;
    private String name;
    private String description;
    private LocalDateTime createdAt;

}
