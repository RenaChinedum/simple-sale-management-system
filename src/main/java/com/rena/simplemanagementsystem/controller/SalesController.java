package com.rena.simplemanagementsystem.controller;

import com.rena.simplemanagementsystem.dto.request.SalesDetailRequest;
import com.rena.simplemanagementsystem.dto.response.SalesDetailResponse;
import com.rena.simplemanagementsystem.service.SalesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sales")
public class SalesController {

    private final SalesService salesService;

    @PostMapping("/create/{productID}/{clientID}/{sellerID}")
    public ResponseEntity<SalesDetailResponse> createSales(@PathVariable Long productID,
                                                           @PathVariable Long clientID,
                                                           @PathVariable Long sellerID,
                                                           @RequestParam int quantity
    )
    {
        SalesDetailRequest request = new SalesDetailRequest(productID,clientID,sellerID,quantity);
        log.info("=====> {}", request);
        return ResponseEntity.ok(salesService.sales(request));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody SalesDetailRequest request)
    {
        return ResponseEntity.ok(salesService.updateSale(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalesDetailResponse>  getSales(@PathVariable Long id)
    {
        return ResponseEntity.ok(salesService.getSales(id));
    }

    @GetMapping("/all-sales")
    public ResponseEntity<List<SalesDetailResponse>> getAllSale(Pageable pageable)
    {
        return ResponseEntity.ok(salesService.allSales(pageable));
    }
}
