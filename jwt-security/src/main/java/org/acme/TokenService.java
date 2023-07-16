package org.acme;

import io.smallrye.jwt.build.Jwt;
import jakarta.inject.Singleton;
import org.acme.dtos.UserDTO;

@Singleton
public class TokenService {


    public String generateTokenForAdmin(){

        return Jwt.issuer("jwt-security")
                .subject("book-cart")
                .groups("admin")
                .claim("user", new UserDTO("admin", "admin"))
                .expiresAt(System.currentTimeMillis()+19000)
                .sign();
    }


    public String generateTokenForUser(){

        return Jwt.issuer("jwt-security")
                .subject("book-cart")
                .groups("user")
                .claim("user", new UserDTO("user", "user"))
                .expiresAt(System.currentTimeMillis()+19000)
                .sign();
    }

}
