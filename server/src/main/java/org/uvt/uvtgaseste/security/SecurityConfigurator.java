package org.uvt.uvtgaseste.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfigurator {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private JwtFilter jwtFilter;
    private String [] WHITE_LIST = {
            "/api/v1/register",
            "/api/v1/login/**",
            "/api/v1/oauth2/**"
    };
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .cors(CorsConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(WHITE_LIST).permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return httpSecurity.build();
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder () {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager (AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
