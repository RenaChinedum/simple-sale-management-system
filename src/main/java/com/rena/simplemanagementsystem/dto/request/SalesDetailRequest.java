package com.rena.simplemanagementsystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalesDetailRequest {
    private Long productID;
    private Long clientID;
    private Long sellerID;
    private int quantity;
    private String price;

    public SalesDetailRequest(Long productID, Long clientID, Long sellerID, int quantity) {
        this.productID = productID;
        this.clientID = clientID;
        this.sellerID = sellerID;
        this.quantity = quantity;
    }

    public SalesDetailRequest(Long clientID, Long productID, Long sellerID) {
        this.clientID = clientID;
        this.productID = productID;
        this.sellerID = sellerID;
    }
}
