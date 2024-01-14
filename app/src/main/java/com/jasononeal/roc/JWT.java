package com.jasononeal.roc;

import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;

public class JWT {
    private final SecretKey key;
    private final String keyString;
    private JwtBuilder jwt;
   public JWT(){
       key = Jwts.SIG.HS256.key().build();
       keyString = Encoders.BASE64.encode(key.getEncoded());
   }

   public String getKeyString(){
       return keyString;
   }

   public String createToken(String sub, Map<String, Object> data){
       JwtBuilder jwt = Jwts.builder().subject(sub).signWith(key);
       data.forEach(jwt::claim);
       return jwt.compact();
   }

   public boolean verifyToken(String token){
       boolean valid;
       try {
           Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
           valid = true;
       } catch (JwtException e) {
           valid = false;
       }

       return valid;
   }

   public void decodeToken(String token){
       Jwt<?, ?> data = Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
   }
}
