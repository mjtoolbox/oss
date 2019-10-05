package com.mjtoolbox.oss.authentication;

import lombok.Data;

import java.io.Serializable;

@Data
//public class AuthenticationBean implements UserDetails, Serializable {
public class JwtRequest implements Serializable {


//    @JsonIgnore
//    private final Long id;

    String username;

    //    @JsonIgnore
    String password;


    public JwtRequest(String you_are_authenticated) {
    }
//    private final Collection<? extends GrantedAuthority> authorities;

//    @Override
//    @JsonIgnore
//    public boolean isAccountNonExpired() {
//        return false;
//    }
//
//    @Override
//    @JsonIgnore
//    public boolean isAccountNonLocked() {
//        return false;
//    }
//
//    @Override
//    @JsonIgnore
//    public boolean isCredentialsNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return false;
//    }
}
