package com.ujazdowski.SocialPortal.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;

public class Jwt {
    private static Key key = MacProvider.generateKey();

    /**
     * Generowanie tokenu dla id użytkownika
     * @param id
     * @return
     */
    public static Token generateToken(Long id){
        return new Token(Jwts.builder()
                            .setSubject(id.toString())
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
