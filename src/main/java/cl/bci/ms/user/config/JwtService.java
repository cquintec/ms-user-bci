
/*
 * @(#)JwtService.java
 *
 * Copyright (c) CQC (Chile). All rights reserved.
 *
 * All rights to this product are owned by CQC and may only
 * be used under the terms of its associated license document.
 * In any event, this notice and the above copyright must always be included
 * verbatim with this file.
 */
package cl.bci.ms.user.config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * JwtService.
 *
 * @author Claudio Quinteros.
 * @version 1.0.0, 13-09-2023
 */
@Service
@RequiredArgsConstructor
public class JwtService {
   private static final String SECRET_KEY="msuserbci";
   public String extractUserName(String token) {
       return extractClaims(token,Claims::getSubject);
   }

   public<T> T extractClaims(String token, Function<Claims, T> claimsResolver){
       final Claims claims=extractAllClaims(token);
       return claimsResolver.apply(claims);
   }

   public String generateToken(UserDetails userDetails){
       return generateToken(new HashMap<>(),userDetails);
   }

    public String generateToken(String userName) {
        Map<String,Object> claims=new HashMap<>();
        return createToken(claims,userName);

    }

    private String createToken(Map<String, Object> claims, String username) {
        return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }
   public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails){
       return Jwts.builder()
               .setClaims(extraClaims)
               .setSubject(userDetails.getUsername())
               .setIssuedAt(new Date(System.currentTimeMillis()))
               .setExpiration(new Date(System.currentTimeMillis()+1000 * 60 * 24))
               .signWith(SignatureAlgorithm.HS256, getSignInKey())
               .compact();
   }

   public boolean isTokenValid(String token, UserDetails userDetails){
       final String username = extractUserName(token);
       return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
   }

    private boolean isTokenExpired(String token) {

       return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
       return extractClaims(token,Claims::getExpiration);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();

   }
    private Key getSignInKey() {
       byte[] keyBites = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBites);
    }

}