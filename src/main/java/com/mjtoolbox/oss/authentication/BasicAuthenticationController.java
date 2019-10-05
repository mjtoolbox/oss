package com.mjtoolbox.oss.authentication;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin()
@RestController
public class BasicAuthenticationController {
//
//    @Resource
//    private AuthenticationManager authenticationManager;
//
//    @Resource
//    private JwtTokenUtil jwtTokenUtil;
//
//    @Resource
//    JwtUserDetailsService jwtUserDetailsService;

//    @GetMapping(path = "/basicauth")
//    public JwtRequest authenticate() {
//        //throw new RuntimeException("Some Error has Happened! Contact Support at ***-***");
//        return new JwtRequest("You are authenticated");
//    }

    @GetMapping(path = "/basicauth")
    public String authenticate() {
        return "You are authenticated";
    }


//    @PostMapping(value = "/authenticate")
//    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
//        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
//        final UserDetails userDetails = jwtUserDetailsService
//                .loadUserByUsername(authenticationRequest.getUsername());
//        final String token = jwtTokenUtil.generateToken(userDetails);
//        return ResponseEntity.ok(new JwtResponse(token));
//    }
//
//    private void authenticate(String username, String password) throws Exception {
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//        } catch (DisabledException e) {
//            throw new Exception("USER_DISABLED", e);
//        } catch (BadCredentialsException e) {
//            throw new Exception("INVALID_CREDENTIALS", e);
//        }
//    }
}
