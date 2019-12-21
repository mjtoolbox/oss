package com.mjtoolbox.oss.authentication;

import com.mjtoolbox.oss.user.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin()
@RestController
@Slf4j
@RequestMapping(value = "/token")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    UserServiceImpl userServiceImpl;


    /**
     * @param loginRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/generate-token", method = RequestMethod.POST)
    public JwtResponse createAuthenticationToken(@RequestBody LoginRequest loginRequest) throws Exception {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final UserPrincipal userPrincipal = userServiceImpl.loadUserPrincipalByEmail(loginRequest.getEmail());
        ;
        log.info("createAuthenticationToken: " + userPrincipal.getUsername());
        return new JwtResponse(200,
                "success",
                userPrincipal.getEmail(),
                jwtTokenUtil.generateToken(userPrincipal),
                userPrincipal.getUsername(),
                (Set) userPrincipal.getAuthorities()
        );
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public JwtResponse logout() throws AuthenticationException {
        return new JwtResponse(200, "success", null, null, null, null);
    }
}
