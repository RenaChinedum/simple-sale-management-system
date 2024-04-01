package com.rena.simplemanagementsystem.util;

import com.rena.simplemanagementsystem.Exception.EntityNotFoundException;
import com.rena.simplemanagementsystem.Repository.ProductRepository;
import com.rena.simplemanagementsystem.Repository.UserRepository;
import com.rena.simplemanagementsystem.dto.response.ClientDetailResponse;
import com.rena.simplemanagementsystem.dto.response.ProductDetailResponse;
import com.rena.simplemanagementsystem.enums.Role;
import com.rena.simplemanagementsystem.model.Product;
import com.rena.simplemanagementsystem.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
public class ApplicationUtil {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    public ClientDetailResponse getClientDetails(User user)
    {
        ClientDetailResponse details = new ClientDetailResponse();
        details.setId(user.getId());
        details.setFirstName(user.getFirstName());
        details.setLastName(user.getLastName());
        details.setEmail(user.getEmail());
        details.setAddress(user.getAddress());
        details.setMobileNumber(user.getMobileNumber());
        return details;
    }

    public User loggedInUser(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    public boolean isAdmin(User user)
    {
        return user.getRole().equals(Role.ADMIN);
    }

    public User getSeller(Long id)
    {
        return userRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Seller not found"));
    }
    public User getClient(Long id)
    {
        return userRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Client not found"));
    }
    public Product getProduct(Long id)
    {
        return  productRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Product not found"));
    }
    public ProductDetailResponse getProductDetail(Product product)
    {
        ProductDetailResponse response = new ProductDetailResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setCategory(product.getCategory());
        response.setPrice(product.getPrice());
        response.setAvailableQuantity(product.getAvailableQuantity());
        response.setCreatedAt(product.getCreatedAt());
        return response;
    }
    public void updateFieldIfNotNullOrEmpty(String value, Consumer<String> setter) {
        if (value != null && !value.isEmpty()) {
            setter.accept(value);
        }
    }
}
