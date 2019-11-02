package com.mjtoolbox.oss.authentication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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
    JwtUserDetailsService jwtUserDetailsService;

    @RequestMapping(value = "/generate-token", method = RequestMethod.POST)
    public JwtResponse<AuthToken> createAuthenticationToken(@RequestBody LoginRequest loginRequest) throws Exception {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(loginRequest.getUsername());
        log.info("createAuthenticationToken: " + userDetails.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return new JwtResponse<>(200, "success", new AuthToken(token, userDetails.getUsername()));
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public JwtResponse<AuthToken> logout() throws AuthenticationException {
        return new JwtResponse<>(200, "success", null);
    }


}
