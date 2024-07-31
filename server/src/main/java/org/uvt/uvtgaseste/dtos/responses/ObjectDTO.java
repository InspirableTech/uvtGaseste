package org.uvt.uvtgaseste.dtos.responses;

import lombok.Getter;
import lombok.Setter;
import org.uvt.uvtgaseste.models.ObjectEntity;
import org.uvt.uvtgaseste.models.Place;
import org.uvt.uvtgaseste.models.Type;

import java.util.Date;

@Getter @Setter
public class ObjectDTO {
    private String name;
    private Type type;
    private String colorHex;
    private String finderMail;
    private Date foundOn;
    private Place place;
    public ObjectDTO(ObjectEntity objectEntity) {
        this.name = objectEntity.getName();
        this.place = objectEntity.getPlace();
        this.type = objectEntity.getType();
        this.colorHex = objectEntity.getColorHex();
    }

    public ObjectDTO(String name, Place place, String type, String colorHex, String finderMail) {
        this.name = name;
        this.place = place;
        this.type = Type.valueOf(type.toUpperCase());
        this.colorHex = colorHex;
        this.finderMail = finderMail;
    }
}
