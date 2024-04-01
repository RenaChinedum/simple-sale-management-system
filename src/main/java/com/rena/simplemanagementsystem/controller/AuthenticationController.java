package com.rena.simplemanagementsystem.controller;

import com.rena.simplemanagementsystem.dto.request.ClientDetailRequest;
import com.rena.simplemanagementsystem.dto.request.SignInRequest;
import com.rena.simplemanagementsystem.dto.response.ClientDetailResponse;
import com.rena.simplemanagementsystem.dto.response.SignInResponse;
import com.rena.simplemanagementsystem.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/sign-up")
    public ResponseEntity<ClientDetailResponse> createClient(@RequestBody ClientDetailRequest request)
    {
        return ResponseEntity.ok(authenticationService.createClient(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<SignInResponse> signIn(@RequestBody SignInRequest request)
    {
        return ResponseEntity.ok(authenticationService.authenticateClient(request));
    }
}
