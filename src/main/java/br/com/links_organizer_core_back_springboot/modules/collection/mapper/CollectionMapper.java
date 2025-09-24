package br.com.links_organizer_core_back_springboot.modules.collection.mapper;

import br.com.links_organizer_core_back_springboot.modules.collection.model.dto.CollectionDTO;
import br.com.links_organizer_core_back_springboot.modules.collection.model.dto.CreateCollectionRequestDTO;
import br.com.links_organizer_core_back_springboot.modules.collection.model.entities.CollectionEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CollectionMapper {

    public CollectionEntity toEntity(
            CreateCollectionRequestDTO createCollectionRequestDTO
    ) {
        return CollectionEntity
                .builder()
                .name(createCollectionRequestDTO.getName())
                .description(createCollectionRequestDTO.getDescription())
                .build();
    }

    public List<CollectionDTO> toListDTO(
            List<CollectionEntity> collectionEntityList
    ) {
        List<CollectionDTO> collectionDTOList = new ArrayList<>();
        collectionEntityList.forEach(collectionEntity -> {
            collectionDTOList.add(
                    CollectionDTO
                            .builder()
                            .id(collectionEntity.getId())
                            .name(collectionEntity.getName())
                            .description(collectionEntity.getDescription())
                            .createdAt(collectionEntity.getCreatedAt())
                            .build()
            );
        });
        return collectionDTOList;
    }

}
