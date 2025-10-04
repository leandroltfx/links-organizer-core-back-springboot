package br.com.links_organizer_core_back_springboot.modules.collection.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCollectionResponseDTO {

    private String message;
    private List<CollectionDTO> collections;

}
