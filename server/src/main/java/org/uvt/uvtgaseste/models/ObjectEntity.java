package org.uvt.uvtgaseste.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.uvt.uvtgaseste.dtos.ObjectDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Entity
@Getter @Setter
@AllArgsConstructor
@Table(name = "objects")
public class ObjectEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "uuid", updatable = false, nullable = false)
    private UUID uuid;
    @Column
    private String name;
    @OneToOne(targetEntity = FinderEntity.class)
    private FinderEntity finderEntity;
    @Column
    @Enumerated(EnumType.STRING)
    private Type type;
    @Column
    @Enumerated(EnumType.STRING)
    private Place place;
    @Column
    private String colorHex;
    @Column
    private LocalDateTime foundDate;
    @Column
    private String compartiment;
    @Column
    private Boolean found;
    @Column
    private Integer PIN;    //autoGenerated
    public ObjectEntity(ObjectDTO objectDTO) {
        this.name = objectDTO.getName();
        this.type = objectDTO.getType();
        this.colorHex = objectDTO.getColorHex();
        this.PIN = new Random().nextInt(9999);
    }

    public ObjectEntity() {

    }
}
