package com.mjtoolbox.oss.authentication;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthenticationBean implements Serializable {
    private String message;

    public AuthenticationBean(String message) {
        this.message = message;
    }
}
