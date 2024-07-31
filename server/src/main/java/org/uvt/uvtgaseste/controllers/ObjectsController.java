package org.uvt.uvtgaseste.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.uvt.uvtgaseste.dtos.responses.ObjectDTO;
import org.uvt.uvtgaseste.dtos.requests.LostDTO;
import org.uvt.uvtgaseste.dtos.responses.RenderDTO;
import org.uvt.uvtgaseste.services.ObjectService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/objects")
public class ObjectsController {
    @Autowired
    private ObjectService objectService;

    /*
    @GetMapping     //listAllObjects
    public ResponseEntity<List<ObjectDTO>> getAllFounds () {
        return ResponseEntity.ok(this.objectService.getAllObjects());
    }
     */
    @RequestMapping
    public ResponseEntity<List<RenderDTO>> getAllPlaces () {
        return ResponseEntity.ok(this.objectService.getAllPlaces());
    }
    @GetMapping("/{uuid}")
    public ResponseEntity<ObjectDTO> getObjectByUuid (@PathVariable UUID uuid) {
        return ResponseEntity.ok(this.objectService.getObjectByUuid(uuid));
    }
    @PostMapping
    public ResponseEntity<ObjectDTO> createObject (@RequestBody ObjectDTO objectDTO) {
        return ResponseEntity.ok(this.objectService.createObject(objectDTO));
    }
    @DeleteMapping("/{uuid}")
    public ResponseEntity<ObjectDTO> deleteBoject (@PathVariable UUID uuid) {     //called after finding
        return ResponseEntity.ok(this.objectService.deleteObject(uuid));
    }
    @PutMapping("/{uuid}")
    public ResponseEntity<Integer> recoverObject (@PathVariable UUID uuid, @RequestBody LostDTO lostDTO) {
        return ResponseEntity.ok(this.objectService.recoverObject(uuid, lostDTO));
    }

    //scan the safe box screen QR section with authentication if the recovery is permitted
}
