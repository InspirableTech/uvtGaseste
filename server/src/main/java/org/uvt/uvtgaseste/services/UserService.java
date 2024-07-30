package org.uvt.uvtgaseste.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.uvt.uvtgaseste.models.UserDTO;
import org.uvt.uvtgaseste.models.UserEntity;
import org.uvt.uvtgaseste.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public List<UserDTO> getAllUsers () {
        List<UserEntity> userEntities = this.userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        for(UserEntity userEntity : userEntities) {
            userDTOList.add(new UserDTO(userEntity));
        }
        return userDTOList;
    }

    public UserDTO getUserById (Long id) {
        Optional<UserEntity> userEntityOptional = this.userRepository.findById(id);
        if(userEntityOptional.isEmpty()) {
            throw new RuntimeException("User not found in the db");
        }
        return new UserDTO(userEntityOptional.get());
    }

    public UserDTO createUser (UserDTO toCreate) {
        Optional<UserEntity> userEntityOptional = this.userRepository.findByEmail(toCreate.getEmail());
        if(userEntityOptional.isPresent()) {
            throw new RuntimeException("User already registered");
        }
        UserEntity user = this.userRepository.save(new UserEntity(toCreate));
        String encodedPass = this.bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPass);
        this.userRepository.save(user);
        return new UserDTO(user);
    }

    public UserDTO updateUser (UserDTO toUpdate, Long id) {
        UserEntity userEntity = this.returnIfPresent(id);
        userEntity.setLastName(toUpdate.getLastName());
        userEntity.setEmail(toUpdate.getEmail());   //to figure it out later
        userEntity.setRole(toUpdate.getRole());
        this.userRepository.save(userEntity);
        return new UserDTO(userEntity);
    }

    public Boolean deleteUSer (Long id) {
        UserEntity userEntity = this.returnIfPresent(id);
        this.userRepository.delete(userEntity);
        return true;
    }

    public UserEntity returnIfPresent (Long id) {
        Optional<UserEntity> userEntityOptional = this.userRepository.findById(id);
        if(userEntityOptional.isEmpty()) {
            throw new RuntimeException("User not found in the db");
        }
        return userEntityOptional.get();
    }
}
