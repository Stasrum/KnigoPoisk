package com.geekbrains.knigopoisk.controllers;

import ch.qos.logback.classic.LoggerContext;
import com.geekbrains.knigopoisk.configs.JWTTokenUtils;
import com.geekbrains.knigopoisk.controllers.facade.AuthControllerApi;
import com.geekbrains.knigopoisk.dto.JwtRequest;
import com.geekbrains.knigopoisk.dto.JwtResponse;
import com.geekbrains.knigopoisk.responsies.ReqErrorResponse;
import com.geekbrains.knigopoisk.services.contracts.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
public class AuthController implements AuthControllerApi {
    private final UserService userService;
    private final JWTTokenUtils jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        log.info("User '{}' is trying to get JWT", authRequest.getUsername());
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException ex) {
            log.warn("There is trying to get JWT with incorrect username or password");
            return new ResponseEntity<>(new ReqErrorResponse(HttpStatus.UNAUTHORIZED.value(), "Incorrect username or password"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        log.info("JWT was sent to user '{}'", authRequest.getUsername());
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
