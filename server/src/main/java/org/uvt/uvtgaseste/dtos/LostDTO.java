package org.uvt.uvtgaseste.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.uvt.uvtgaseste.models.Place;
import org.uvt.uvtgaseste.models.Type;

import java.util.List;
//Mass e-mail or mass phone SMS to the Finders that satistifes the conditions
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class LostDTO {
    private Type type;
    private String name; //idk
    private List<Place> possiblePlace;
    private String ownerMail;
    private String phoneNumber;
}
