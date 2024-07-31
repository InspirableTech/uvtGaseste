package org.uvt.uvtgaseste.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uvt.uvtgaseste.dtos.responses.ObjectDTO;
import org.uvt.uvtgaseste.dtos.requests.LostDTO;
import org.uvt.uvtgaseste.dtos.responses.RenderDTO;
import org.uvt.uvtgaseste.models.ObjectEntity;
import org.uvt.uvtgaseste.models.Place;
import org.uvt.uvtgaseste.repositories.ObjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

//esti la distanta si presupui ca obiectul este la uvt
//ca sa nu il cauti altundeva, verifici aceasta aplicatie

@Service
public class ObjectService {
    @Autowired
    private ObjectRepository objectRepository;

    //discutabil
    public List<RenderDTO> getAllPlaces () {
        List<RenderDTO> renderElements = new ArrayList<>();
        for(ObjectDTO objectDTO : this.getAllObjects()) {
            renderElements.add(new RenderDTO(objectDTO.getFoundOn(), objectDTO.getPlace(), objectDTO.getType()));
        }
        return renderElements;
    }

    public List<ObjectDTO> getAllObjects () {
        List<ObjectEntity> allObjectEntities = this.objectRepository.findAll();
        List<ObjectDTO> allObjectsDTO = new ArrayList<>();
        for(ObjectEntity foundEntity : allObjectEntities) {
            allObjectsDTO.add(new ObjectDTO(foundEntity));
        }
        return allObjectsDTO;
    }

    public ObjectDTO getObjectByUuid (UUID uuid) {
        Optional<ObjectEntity> foundObjectEntityOpt = this.objectRepository.findByUuid(uuid);
        if(foundObjectEntityOpt.isEmpty()) {
            throw new RuntimeException("The object has not been found");
        }
        ObjectEntity foundEntity = foundObjectEntityOpt.get();
        return new ObjectDTO(foundEntity);
    }
    @Transactional
    public ObjectDTO createObject (ObjectDTO objectDTO) {
        ObjectEntity foundObjectEntity = new ObjectEntity(objectDTO);
        return new ObjectDTO(this.objectRepository.save(foundObjectEntity));
    }

    public ObjectDTO deleteObject (UUID uuid) {
        ObjectDTO objectDTO = this.getObjectByUuid(uuid);
        this.objectRepository.deleteByUuid(uuid);
        return objectDTO;
    }

    public Integer recoverObject (UUID uuid, LostDTO lostDTO) {
        ObjectEntity foundEntity = this.returnIfPresent(uuid);
        foundEntity.setFound(true);
        return foundEntity.getPIN();
    }

    private ObjectEntity returnIfPresent (UUID uuid) {
        Optional<ObjectEntity> objectEntity = this.objectRepository.findByUuid(uuid);
        if (objectEntity.isEmpty()) {
            throw new RuntimeException("Lost object not found in the database");
        }
        return objectEntity.get();
    }

}
