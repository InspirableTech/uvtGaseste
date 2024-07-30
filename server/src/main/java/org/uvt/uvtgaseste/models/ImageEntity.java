package org.uvt.uvtgaseste.models;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "images")
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, unique = true)
    private UUID uuid;
    @Column
    private String path;
    @OneToOne(targetEntity = ObjectEntity.class)
    private ObjectEntity object;

    public ImageEntity () {

    }
}
