package org.uvt.uvtgaseste.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.uvt.uvtgaseste.models.Place;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class RenderDTO {
    private Place place;
    private int itemsLost;
}
