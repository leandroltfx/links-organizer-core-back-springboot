package br.com.links_organizer_core_back_springboot.modules.collection.repository;

import br.com.links_organizer_core_back_springboot.modules.collection.model.entities.CollectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CollectionRepository extends JpaRepository<CollectionEntity, UUID> {

    List<CollectionEntity> findAllByUserId(UUID userId);

}
