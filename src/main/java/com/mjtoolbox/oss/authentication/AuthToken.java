package com.mjtoolbox.oss.authentication;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthToken implements Serializable {

    private final String token;
    private final String username;

    public AuthToken(String token, String username) {
        this.token = token;
        this.username = username;
    }
}
