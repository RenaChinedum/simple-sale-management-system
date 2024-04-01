package com.rena.simplemanagementsystem.controller;

import com.rena.simplemanagementsystem.dto.response.ClientDetailResponse;
import com.rena.simplemanagementsystem.dto.response.ProductDetailResponse;
import com.rena.simplemanagementsystem.model.Sales;
import com.rena.simplemanagementsystem.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/report")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/sales-with-a-week")
    public ResponseEntity<List<Sales>> salesWithinARange(){
        return ResponseEntity.ok(reportService.getSales_Within_LastOneWeek());
    }

    @GetMapping("/total_number_of_sales")
    public ResponseEntity<Integer> totalNumberOfSales(){
        return ResponseEntity.ok(reportService.getTotalNumberOfSales());
    }

    @GetMapping("/revenue")
    public ResponseEntity<String> getRevenue(){
        return ResponseEntity.ok(reportService.getTotalRevenue());
    }

    @GetMapping("/top-selling-product")
    public ResponseEntity<List<ProductDetailResponse>> topSellingProduct(){
        return ResponseEntity.ok(reportService.topSellingProduct());
    }

    @GetMapping("/top-performing-sales_person")
    public ResponseEntity<List<ClientDetailResponse>> topPerformingSalesPerson(){
        return ResponseEntity.ok(reportService.topPerformingSalesPerson());
    }

    @GetMapping("/total-number-of-client")
    public ResponseEntity<Integer> getTotalNumberOfClients(){
        return ResponseEntity.ok(reportService.getTotalNumberOfClients());
    }

    @GetMapping("/top-buying-client")
    public ResponseEntity<List<ClientDetailResponse>> topBuyingClient(){
        return ResponseEntity.ok(reportService.topBuyingClient());
    }

    @GetMapping("/client-in-a-location")
    public ResponseEntity<List<ClientDetailResponse>> clientFromSimilarLocation(){
        return ResponseEntity.ok(reportService.clientFromSimilarLocation());
    }
}
