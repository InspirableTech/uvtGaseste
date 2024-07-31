package org.uvt.uvtgaseste.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${app-jwt-secret}")
    private String SECRET_KEY;
    @Value("${app-jwt-secret}")
    private String EXPIRATION;

    public SecretKey getKey () {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateToken (Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Integer.parseInt(this.EXPIRATION)))
                .signWith(this.getKey(), SignatureAlgorithm.ES256)
                .compact();
    }
    public String generateTokenFromAuthentication (Authentication authentication) {
        Map<String, Object> claims = Map.of();
        return this.generateToken(claims, authentication.getName());
    }
    public String generateTokenFromOAuth2User (OAuth2User oAuth2User) {
        Map<String, Object> claims = new HashMap<>(oAuth2User.getAttributes());
        return this.generateToken(claims, oAuth2User.getAttribute("email"));
    }

    public Claims extractAllClaims (String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(this.getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extractClaim (String token, Function<Claims, T> claimResolver) {
        final Claims claims = this.extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    public String extractUserFromToken (String token) {
        return this.extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration (String token) {
        return this.extractClaim(token, Claims::getExpiration);
    }

    public boolean isTokenExpired (String token) {
        return extractExpiration(token).before(new Date());
    }

    public boolean isTokenValid (String token, CustomUser customUser) {
        final String username = extractUserFromToken(token);
        return isTokenExpired(token) && customUser.getUsername().equals(username);
    }
}
