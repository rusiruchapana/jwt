package com.rusiruchapana.security.service;

import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class JWTService {
    private static final String secret = "hQXxxYG1gFlfmTaQQLOnGjp6cTkfNI8UtNMc6Jo0nCO2v826bhBr23UFn7hyc35jfa54EksorFoBoAprHW/GIA==";
    private static final Long expiration = TimeUnit.MINUTES.toMillis(30);


    public String generateToken(UserDetails userDetails){
        Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusMillis(expiration)))
                .signWith(getSigningKey())
                .compact();


        return null;
    }

    private Key getSigningKey() {
        byte[] decodedKey = Base64.getDecoder().decode(secret);
        SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
        return originalKey;
    }


}
