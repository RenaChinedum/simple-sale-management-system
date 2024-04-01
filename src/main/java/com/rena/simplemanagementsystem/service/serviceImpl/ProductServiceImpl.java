package com.rena.simplemanagementsystem.service.serviceImpl;

import com.rena.simplemanagementsystem.Exception.EntityNotFoundException;
import com.rena.simplemanagementsystem.Exception.UnauthorisedAccessException;
import com.rena.simplemanagementsystem.Repository.ProductRepository;
import com.rena.simplemanagementsystem.dto.request.ProductDetailRequest;
import com.rena.simplemanagementsystem.dto.response.ProductDetailResponse;
import com.rena.simplemanagementsystem.model.Product;
import com.rena.simplemanagementsystem.model.User;
import com.rena.simplemanagementsystem.service.ProductService;
import com.rena.simplemanagementsystem.util.ApplicationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ApplicationUtil util;

    @Override
    public ProductDetailResponse createProduct(ProductDetailRequest request)
    {
        User user = util.loggedInUser();
        if (util.isAdmin(user)){
            log.info("===> {}", user.getRole());
            Product product = getProduct(request);
            productRepository.save(product);
            return util.getProductDetail(product);
        }
        throw new UnauthorisedAccessException("You're not authorised to create a product");
    }
    @Override
    public ProductDetailResponse updateProduct(ProductDetailRequest request, Long id)
    {
        User user = util.loggedInUser();
        if (util.isAdmin(user)){
            log.info("===> {}", user.getRole());
            Product product =  productRepository.findById(id).orElseThrow(()
                    -> new EntityNotFoundException("Product not found"));
            util.updateFieldIfNotNullOrEmpty(request.getName(), product::setName);
            util.updateFieldIfNotNullOrEmpty(request.getDescription(), product::setDescription);
            util.updateFieldIfNotNullOrEmpty(request.getCategory(), product::setCategory);
            util.updateFieldIfNotNullOrEmpty(request.getPrice(), product::setPrice);
            String quantity = String.valueOf(request.getQuantity());
            if (quantity != null)
                product.setAvailableQuantity(Integer.parseInt(quantity));

            productRepository.save(product);
            return util.getProductDetail(product);
        }
        throw new UnauthorisedAccessException("You're not authorised to create a product");
    }

    @Override
    public ProductDetailResponse getAProduct(Long id)
    {
        Product product = productRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Product not found"));
        return util.getProductDetail(product);
    }

    @Override
    public String deleteProduct(Long id)
    {
        User user = util.loggedInUser();
        if (util.isAdmin(user)) {
            log.info("===> {}", user.getRole());
            Product product = productRepository.findById(id).orElseThrow(()
                    -> new EntityNotFoundException("Product not found"));
            productRepository.delete(product);
            return "Deleted  successfully";
        }else
            throw new UnauthorisedAccessException("You're not authorised to create a product");
    }

    @Override
    public List<ProductDetailResponse> allProduct(Pageable pageable)
    {
        List<ProductDetailResponse> productDetailResponseList = new ArrayList<>();
        Page<Product> productPage = productRepository.findAll(pageable);
        List<Product> productList = productPage.getContent();
        log.info("===> {}", productList.size());
        for (Product product : productList){
           ProductDetailResponse response = util.getProductDetail(product);
           productDetailResponseList.add(response);
        }
        return productDetailResponseList;
    }


    private Product getProduct(ProductDetailRequest request)
    {
       Product product = new Product();
        setProduct(request, product);
        return product;
    }

    private void setProduct(ProductDetailRequest request, Product product) {
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setCategory(request.getCategory());
        product.setPrice(request.getPrice());
        product.setAvailableQuantity(request.getQuantity());
    }


}
