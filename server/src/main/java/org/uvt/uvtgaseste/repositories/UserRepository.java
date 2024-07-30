package org.uvt.uvtgaseste.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.uvt.uvtgaseste.models.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail (String email);
}
