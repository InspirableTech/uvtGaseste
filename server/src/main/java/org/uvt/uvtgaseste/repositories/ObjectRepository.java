package org.uvt.uvtgaseste.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.uvt.uvtgaseste.models.ObjectEntity;
import org.uvt.uvtgaseste.models.Place;
import org.uvt.uvtgaseste.models.Type;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ObjectRepository extends JpaRepository <ObjectEntity, Long> {
    Optional<ObjectEntity> findByUuid (UUID uuid);
    ObjectEntity deleteByUuid (UUID uuid);
    ObjectEntity findByName (String name);
    List<ObjectEntity> getObjectEntitiesByPlace (Place place);
}
