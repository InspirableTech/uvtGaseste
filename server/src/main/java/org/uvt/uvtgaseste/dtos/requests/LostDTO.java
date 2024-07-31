package org.uvt.uvtgaseste.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.uvt.uvtgaseste.models.Place;
import org.uvt.uvtgaseste.models.Type;

import java.util.List;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class LostDTO {
    private Type type;
    private String name;
    private List<Place> possiblePlaces;
    private String ownerMail;
    private String phoneNumber;
}
