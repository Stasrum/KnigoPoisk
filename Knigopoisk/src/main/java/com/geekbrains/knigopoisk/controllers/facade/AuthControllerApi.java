package com.geekbrains.knigopoisk.controllers.facade;

import com.geekbrains.knigopoisk.dto.JwtRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthControllerApi {

    @PostMapping("/auth")
    ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest);
}
