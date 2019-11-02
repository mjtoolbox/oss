package com.mjtoolbox.oss.authentication;

import lombok.Data;

import java.io.Serializable;

@Data
public class JwtResponse<T> implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private int status;
    private String message;
    private Object authToken;


    public JwtResponse(int status, String message, Object authToken) {
        this.status = status;
        this.message = message;
        this.authToken = authToken;
    }

}
