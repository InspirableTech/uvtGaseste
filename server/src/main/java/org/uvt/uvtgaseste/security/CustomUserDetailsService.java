package org.uvt.uvtgaseste.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.uvt.uvtgaseste.models.UserEntity;
import org.uvt.uvtgaseste.repositories.UserRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public CustomUser loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntityOptional = this.userRepository.findByEmail(username);
        if(userEntityOptional.isEmpty()) {
            throw new RuntimeException("Couldn't create the UserDetails object. User not found");
        }
        UserEntity userEntity = userEntityOptional.get();
        CustomUser user = new CustomUser(
                userEntity.getEmail(),
                userEntity.getPassword(),
                null,
                null
        );
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new CustomGrantedAuthority(userEntity.getRole()));
        return user;
    }
}
