package com.rena.simplemanagementsystem.service;

import com.rena.simplemanagementsystem.dto.response.ClientDetailResponse;
import com.rena.simplemanagementsystem.dto.response.ProductDetailResponse;
import com.rena.simplemanagementsystem.model.Sales;

import java.util.List;

public interface ReportService {
    List<Sales> getSales_Within_LastOneWeek();

    int getTotalNumberOfSales();

    String getTotalRevenue();

    List<ProductDetailResponse> topSellingProduct();

    List<ClientDetailResponse> topPerformingSalesPerson();

    int getTotalNumberOfClients();

    List<ClientDetailResponse> topBuyingClient();

    List<ClientDetailResponse> clientFromSimilarLocation();
}
