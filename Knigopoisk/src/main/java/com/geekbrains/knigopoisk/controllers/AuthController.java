package com.geekbrains.knigopoisk.controllers;

import com.geekbrains.knigopoisk.configs.JWTTokenUtils;
import com.geekbrains.knigopoisk.controllers.facade.AuthControllerApi;
import com.geekbrains.knigopoisk.dto.JwtRequest;
import com.geekbrains.knigopoisk.dto.JwtResponse;
import com.geekbrains.knigopoisk.responsies.ReqErrorResponse;
import com.geekbrains.knigopoisk.services.contracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
public class AuthController implements AuthControllerApi {
    private final UserService userService;
    private final JWTTokenUtils jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException ex) {
            return new ResponseEntity<>(new ReqErrorResponse(HttpStatus.UNAUTHORIZED.value(), "Incorrect username or password"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
