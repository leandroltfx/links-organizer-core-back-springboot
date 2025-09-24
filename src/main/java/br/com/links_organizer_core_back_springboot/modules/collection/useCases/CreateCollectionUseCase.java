package br.com.links_organizer_core_back_springboot.modules.collection.useCases;

import br.com.links_organizer_core_back_springboot.modules.collection.mapper.CollectionMapper;
import br.com.links_organizer_core_back_springboot.modules.collection.model.dto.CreateCollectionResponseDTO;
import br.com.links_organizer_core_back_springboot.modules.collection.model.entities.CollectionEntity;
import br.com.links_organizer_core_back_springboot.modules.collection.repository.CollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateCollectionUseCase {

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private CollectionMapper collectionMapper;

    public CreateCollectionResponseDTO execute(
            CollectionEntity collectionEntity,
            UUID userId
    ) {
        collectionEntity.setUserId(userId);
        this.collectionRepository.save(collectionEntity);

        var collections = this.collectionRepository.findAllByUserId(userId);

        return CreateCollectionResponseDTO
                .builder()
                .message("Coleção cadastrada com sucesso!")
                .collections(this.collectionMapper.toListDTO(collections))
                .build();
    }

}
