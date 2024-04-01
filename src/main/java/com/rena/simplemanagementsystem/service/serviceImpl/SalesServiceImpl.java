package com.rena.simplemanagementsystem.service.serviceImpl;

import com.rena.simplemanagementsystem.Exception.CustomException;
import com.rena.simplemanagementsystem.Exception.EntityNotFoundException;
import com.rena.simplemanagementsystem.Exception.UnauthorisedAccessException;
import com.rena.simplemanagementsystem.Repository.ProductRepository;
import com.rena.simplemanagementsystem.Repository.SalesRepository;
import com.rena.simplemanagementsystem.Repository.UserRepository;
import com.rena.simplemanagementsystem.dto.request.SalesDetailRequest;
import com.rena.simplemanagementsystem.dto.response.SalesDetailResponse;
import com.rena.simplemanagementsystem.model.Product;
import com.rena.simplemanagementsystem.model.Sales;
import com.rena.simplemanagementsystem.model.User;
import com.rena.simplemanagementsystem.service.SalesService;
import com.rena.simplemanagementsystem.util.ApplicationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
@Slf4j
@Service
@RequiredArgsConstructor
public class SalesServiceImpl implements SalesService {

    private final SalesRepository salesRepository;
   private final UserRepository userRepository;
   private final ProductRepository productRepository;
   private final ApplicationUtil util;


   @Override
   public SalesDetailResponse sales(SalesDetailRequest request)
   {
       User seller = util.getSeller(request.getSellerID());
       User client = util.getClient(request.getClientID());
       Product product = util.getProduct(request.getProductID());
       if (util.isAdmin(seller)){
           log.info("=====>{}", seller.getRole());
          Sales sales = getSales(request);
          if(request.getQuantity() > product.getAvailableQuantity())
              throw new CustomException("Sorry, we have only " + product.getAvailableQuantity() +" available");
          sales.setQuantity(request.getQuantity());
          sales.setTotal(String.valueOf(Integer.parseInt(product.getPrice()) * request.getQuantity()));
          product.setAvailableQuantity(product.getAvailableQuantity() - request.getQuantity());
          productRepository.save(product);
          salesRepository.save(sales);
          return SalesDetailResponse.builder()
                  .id(sales.getId())
                  .client(client)
                  .seller(seller)
                  .total(sales.getTotal())
                  .creationDate(sales.getCreatedAt())
                  .build();

       }
       throw new UnauthorisedAccessException("You're not authorised to make sales");
   }

   @Override
   public SalesDetailResponse getSales(Long id)
   {
       Sales sales = salesRepository.findById(id).orElseThrow(()
               -> new EntityNotFoundException("Sales not found"));
       User seller = util.getSeller(sales.getSellerID());
       User client = util.getClient(sales.getClientID());
       Product product = util.getProduct(sales.getProductID());
      return SalesDetailResponse.builder()
              .id(sales.getId())
              .client(client)
              .seller(seller)
              .total(sales.getTotal())
              .creationDate(sales.getCreatedAt())
              .build();
   }

   @Override
   public String updateSale(Long id, SalesDetailRequest request)
   {
       Sales sales = salesRepository.findById(id).orElseThrow(()
               -> new EntityNotFoundException("Sales not found"));
       String quantity = String.valueOf(request.getQuantity());
       if (quantity != null)
           sales.setQuantity(Integer.parseInt(quantity));

       if(request.getPrice() != null)
           sales.setTotal(request.getPrice());

       salesRepository.save(sales);
       return "Updated successfully";
   }

   @Override
   public List<SalesDetailResponse> allSales(Pageable pageable)
   {
       List<SalesDetailResponse> salesDetailsList = new ArrayList<>();
       Page<Sales> salesPage = salesRepository.findAll(pageable);
       List<Sales> salesList = salesPage.getContent();
       log.info("====>{}", salesList.size());
       for (Sales sales : salesList) {
           SalesDetailResponse response = new SalesDetailResponse();
           response.setId(sales.getId());
           response.setClient(util.getClient(sales.getClientID()));
           response.setSeller(util.getSeller(sales.getSellerID()));
           response.setTotal(sales.getTotal());
           response.setCreationDate(sales.getCreatedAt());
           salesDetailsList.add(response);
       }
       return salesDetailsList;
   }
    private void updateFieldIfNotNullOrEmpty(String value, Consumer<String> setter) {
        if (value != null && !value.isEmpty()) {
            setter.accept(value);
        }
    }

   private Sales getSales(SalesDetailRequest request)
   {
       Sales sales = new Sales();
       sales.setSellerID(request.getSellerID());
       sales.setClientID(request.getClientID());
       sales.setProductID(request.getProductID());
       return sales;
   }

}
