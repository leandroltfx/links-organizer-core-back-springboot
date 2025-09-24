package br.com.links_organizer_core_back_springboot.modules.collection.controllers;

import br.com.links_organizer_core_back_springboot.modules.collection.mapper.CollectionMapper;
import br.com.links_organizer_core_back_springboot.modules.collection.model.dto.CreateCollectionRequestDTO;
import br.com.links_organizer_core_back_springboot.modules.collection.model.dto.CreateCollectionResponseDTO;
import br.com.links_organizer_core_back_springboot.modules.collection.useCases.CreateCollectionUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/collections")
public class CollectionController {

    @Autowired
    private CreateCollectionUseCase createCollectionUseCase;

    @Autowired
    private CollectionMapper collectionMapper;

    @PostMapping
    public ResponseEntity<CreateCollectionResponseDTO> createCollection(
            @Valid @RequestBody CreateCollectionRequestDTO createCollectionRequestDTO,
            HttpServletRequest request
    ) {
        var userId = request.getAttribute("user_id");
        return ResponseEntity.status(HttpStatus.CREATED).body(
                this.createCollectionUseCase.execute(
                        this.collectionMapper.toEntity(createCollectionRequestDTO),
                        UUID.fromString(userId.toString())
                )
        );
    }

}
