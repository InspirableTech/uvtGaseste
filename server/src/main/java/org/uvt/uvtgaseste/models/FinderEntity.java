package org.uvt.uvtgaseste.models;

import jakarta.persistence.*;

@Entity
@Table(name = "finders")
public class FinderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String uvtMail;
    @Column
    private String phoneNumber;

    public FinderEntity () {

    }
}
