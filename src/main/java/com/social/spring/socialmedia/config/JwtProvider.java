package com.social.spring.socialmedia.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtProvider {
    private static final SecretKey secretKey = Keys.hmacShaKeyFor(JwtConstants.SECRET_KEY.getBytes());

    public static String generateToken(Authentication auth) {
        String jwt = Jwts.builder()
                .setIssuer("Shubham")
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+86400000)) //JWT TOKEN VALID FOR 24 HOURS
                .claim("email", auth.getName())
                .signWith(secretKey)
                .compact();
        return jwt;
    }

    public static String getEmailFromJwtToken(String jwt) {
        //AS WE HAVE GIVEN THE JWT TOKEN AS BEARER TOKEN[REFER TO POSTMAN], IT WILL BE IN THIS FORMAT
        //Bearer 'TOKEN' [this is how the token will be looking like]

        jwt = jwt.substring(7); //skips the first 7 letters[ie: "Bearer" Key word will be omitted]
        Claims claims = Jwts
                        .parserBuilder()
                        .setSigningKey(secretKey)
                        .build().parseClaimsJws(jwt)
                        .getBody();
        String email = String.valueOf(claims.get("email"));

        return email;
    }
}
