package org.uvt.uvtgaseste.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.uvt.uvtgaseste.models.Place;
import org.uvt.uvtgaseste.models.Type;

import java.util.Date;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class RenderDTO {
    private Date foundOn;
    private Place foundAt;
    private Type itemType;
}
