package com.ujazdowski.SocialPortal.utils;

import com.ujazdowski.SocialPortal.model.tables.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.joda.time.LocalDateTime;

import java.security.Key;
import java.util.Observable;
import java.util.Optional;

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
    private static Long decryptToken(Token token){
        return Long.parseLong(Jwts.parser()
                                    .setSigningKey(key)
                                    .parseClaimsJws(token.getToken())
                                    .getBody()
                                    .getSubject());
    }

    /**
     * Sprawdza czy token nie wygasl
     * @param token
     * @return false jezeli token jest wazny true w przeciwnym przypadku
     */
    private static Boolean expired(Token token){

        return false;
    }

    /**
     * Sprawdzenie czy token nie wygas i czy jest poprawny
     * @param token
     * @return id uzytkownika lub null
     */
    public static Optional<Long> validateToken(Token token){
        return (!expired(token) ? Optional.of(decryptToken(token)) : Optional.empty());
    }
}
