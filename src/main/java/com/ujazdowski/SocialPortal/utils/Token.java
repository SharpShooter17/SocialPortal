package com.ujazdowski.SocialPortal.utils;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement
public class Token {
    private String token;
    public Token(String token){
        this.token = token;
    }
}
