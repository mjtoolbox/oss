package com.mjtoolbox.oss.authentication;

import com.mjtoolbox.oss.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    @Resource
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws ResourceNotFoundException {

        log.info("************ loadUserByUsername " + email);
        if ("michael.jo@gmail.com".equals(email)) {
            return new User("michael.jo@gmail.com", "$2a$12$R5x9qtnCadcEi/QcmbCpI.71j6UPAt3mGNnyYukkPd1gYxPUPdHru",
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + email);
        }
//        if ("mymy".equals(email)) {
//            return new User("mymy", "$2a$10$q3PBzO9xZ8fw4917ZdvE7uD7BA6WlUDDqyIpeBmKb5XbEKdm4gfe6",
//                    new ArrayList<>());
//        } else {
//            throw new UsernameNotFoundException("User not found with username: " + email);
//        }

//        // Load User from DB
//        UserOss user = userRepository.findByEmail(email);
//
//        log.info("************************* " + user.getEmail() + " PW: " + user.getPassword());
////        return UserPrincipal.create(user);
////        return new User(user.getUser_name(), user.getPassword(), new ArrayList<Role>(user.getRoles()));
//        return User.withUsername(user.getUser_name())
//                .password(user.getPassword())
//                .authorities(user.getRoles())
//                .build();
    }

//    public UserDetails loadUserById(Long id) {
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
//        return UserPrincipal.create(user);
//    }
}
