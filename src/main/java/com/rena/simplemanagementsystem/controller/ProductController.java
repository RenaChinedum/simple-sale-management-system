package com.rena.simplemanagementsystem.controller;

import com.rena.simplemanagementsystem.dto.request.ProductDetailRequest;
import com.rena.simplemanagementsystem.dto.response.ProductDetailResponse;
import com.rena.simplemanagementsystem.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ProductDetailResponse> createProduct(@RequestBody ProductDetailRequest request)
    {
        return ResponseEntity.ok(productService.createProduct(request));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<ProductDetailResponse> updateProduct(@RequestBody ProductDetailRequest request, @PathVariable Long id)
    {
        return ResponseEntity.ok(productService.updateProduct(request, id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDetailResponse> getAProduct(@PathVariable Long id)
    {
        return ResponseEntity.ok(productService.getAProduct(id));
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id)
    {
        return ResponseEntity.ok(productService.deleteProduct(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductDetailResponse>> allProduct(Pageable pageable)
    {
        return ResponseEntity.ok(productService.allProduct(pageable));
    }

}
