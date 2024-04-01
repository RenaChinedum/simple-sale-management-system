package com.rena.simplemanagementsystem.service;

import com.rena.simplemanagementsystem.dto.request.ProductDetailRequest;
import com.rena.simplemanagementsystem.dto.response.ProductDetailResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    ProductDetailResponse createProduct(ProductDetailRequest request);

    ProductDetailResponse updateProduct(ProductDetailRequest request, Long id);

    ProductDetailResponse getAProduct(Long id);

    String deleteProduct(Long id);

    List<ProductDetailResponse> allProduct(Pageable pageable);
}
