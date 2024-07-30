package org.uvt.uvtgaseste.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Entity
@Table(name = "users")
@Getter @Setter
public class UserEntity {
    @Id
    private Long id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private Role role;          //to solve the many roles issue
    @OneToMany(
            targetEntity = ObjectEntity.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "uuid"
    )
    private List<ObjectEntity> foundObjects;
    @OneToMany(
            targetEntity = ObjectEntity.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "uuid"
    )
    private List<ObjectEntity> lostObjects;

    public UserEntity () {

    }

    public UserEntity (UserDTO userDTO) {
        this.firstName = userDTO.getFirstName();
        this.lastName = userDTO.getLastName();
        this.password =
        this.email = userDTO.getEmail();
        this.role = userDTO.getRole();
    }
}
