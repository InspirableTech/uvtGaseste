package org.uvt.uvtgaseste.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class UserDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;

    public UserDTO (UserEntity entity) {
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
        this.email = entity.getEmail();
        this.password = entity.getPassword();
        this.role = entity.getRole();
    }
}
