package com.rena.simplemanagementsystem.service;

import com.rena.simplemanagementsystem.dto.request.ClientDetailRequest;
import com.rena.simplemanagementsystem.dto.response.ClientDetailResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    ClientDetailResponse updateClientDetail(ClientDetailRequest request);

    String deleteClient(Long id);

    List<ClientDetailResponse> allClient(Pageable pageable);

    ClientDetailResponse getAClient(Long id);
}
