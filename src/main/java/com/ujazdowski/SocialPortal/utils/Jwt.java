package com.ujazdowski.SocialPortal.utils;

import com.ujazdowski.SocialPortal.model.tables.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.joda.time.LocalDateTime;

import java.security.Key;

public class Jwt {
    private static Key key = MacProvider.generateKey();

    /**
     * Generowanie tokenu dla id użytkownika
     * @param user
     * @return
     */
    public static Token generateToken(User user){
        LocalDateTime expiration = new LocalDateTime();
        expiration.plusSeconds(7200);

        return new Token(Jwts.builder()
                            .setSubject(user.getId().toString())
                            .setExpiration( expiration.toDate())
                            .claim("name", user.getFirstName() + " " + user.getSecondName())
                            .signWith(SignatureAlgorithm.HS256, Jwt.key)
                            .compact());
    }

    /**
     * Odkodowanie tokenu użytkownika
     * @param token
     * @return Long Id użytkownika
     */
    public static Long decryptToken(Token token){
        return Long.parseLong(Jwts.parser()
                                    .setSigningKey(key)
                                    .parseClaimsJws(token.getToken())
                                    .getBody()
                                    .getSubject());
    }
}
