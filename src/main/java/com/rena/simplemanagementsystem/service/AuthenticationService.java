package com.rena.simplemanagementsystem.service;

import com.rena.simplemanagementsystem.dto.request.ClientDetailRequest;
import com.rena.simplemanagementsystem.dto.request.SignInRequest;
import com.rena.simplemanagementsystem.dto.response.ClientDetailResponse;
import com.rena.simplemanagementsystem.dto.response.SignInResponse;

public interface AuthenticationService {
    ClientDetailResponse createClient(ClientDetailRequest request);

    SignInResponse authenticateClient(SignInRequest request);
}
