package com.rena.simplemanagementsystem.service;

import com.rena.simplemanagementsystem.dto.request.SalesDetailRequest;
import com.rena.simplemanagementsystem.dto.response.SalesDetailResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SalesService {
    SalesDetailResponse sales(SalesDetailRequest request);

    SalesDetailResponse getSales(Long id);

    String updateSale(Long id, SalesDetailRequest request);

    List<SalesDetailResponse> allSales(Pageable pageable);
}
