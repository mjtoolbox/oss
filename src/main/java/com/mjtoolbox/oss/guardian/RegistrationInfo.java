package com.mjtoolbox.oss.guardian;

import lombok.Data;

import java.io.Serializable;

@Data
public class RegistrationInfo implements Serializable {

    private String guardian_name;
    private String relationship;
    private String cell_phone;
    private String email;
    private String home_phone;
    private String address;
    private String city;
    private String province;
    private String postal_code;
    private String password;
    private boolean enabled;

}
