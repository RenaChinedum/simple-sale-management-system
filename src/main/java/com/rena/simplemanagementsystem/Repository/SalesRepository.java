package com.rena.simplemanagementsystem.Repository;

import com.rena.simplemanagementsystem.model.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SalesRepository extends JpaRepository<Sales, Long> {

    @Query(value = "SELECT * FROM sales_table WHERE created_at >= CURRENT_DATE - INTERVAL '7 DAY'", nativeQuery = true)
    List<Sales> findSalesWithinLastWeek();
    @Query(value = "SELECT productID FROM sales_table GROUP BY productID ORDER BY SUM(quantity) DESC LIMIT 5", nativeQuery = true)
    List<Long> findTop5ProductsBySales();
    @Query(value = "SELECT sellerID FROM sales_table GROUP BY sellerID ORDER BY SUM(quantity) DESC LIMIT 5", nativeQuery = true)
    List<Long> findSellerWithHighestSales();

    @Query(value = "SELECT clientID FROM sales_table GROUP BY clientID ORDER BY SUM(CAST(total AS numeric)) DESC LIMIT 5", nativeQuery = true)
    List<Long> findHighestBuyingClient();

}
