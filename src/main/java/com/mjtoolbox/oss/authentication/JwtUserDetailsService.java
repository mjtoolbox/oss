package com.mjtoolbox.oss.authentication;

import com.mjtoolbox.oss.user.UserOss;
import com.mjtoolbox.oss.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    @Resource
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws ResourceNotFoundException {

        log.info("************ loadUserByUsername " + email);
        UserOss user = userRepository.findByEmail(email);

        log.info("************************* " + user.getEmail() + " PW: " + user.getPassword());
        return User.withUsername(user.getEmail())
                .password(user.getPassword())
//                .authorities(user.getRoles())
                .authorities(Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")))
                .build();
    }
}
