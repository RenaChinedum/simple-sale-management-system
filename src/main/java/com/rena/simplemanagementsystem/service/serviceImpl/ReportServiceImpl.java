package com.rena.simplemanagementsystem.service.serviceImpl;

import com.rena.simplemanagementsystem.Exception.UnauthorisedAccessException;
import com.rena.simplemanagementsystem.Repository.ProductRepository;
import com.rena.simplemanagementsystem.Repository.SalesRepository;
import com.rena.simplemanagementsystem.Repository.UserRepository;
import com.rena.simplemanagementsystem.dto.response.ClientDetailResponse;
import com.rena.simplemanagementsystem.dto.response.ProductDetailResponse;
import com.rena.simplemanagementsystem.model.Sales;
import com.rena.simplemanagementsystem.model.User;
import com.rena.simplemanagementsystem.service.ReportService;
import com.rena.simplemanagementsystem.util.ApplicationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final SalesRepository salesRepository;

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    private final ApplicationUtil util;


    @Override
    public List<Sales> getSales_Within_LastOneWeek() {
        User user = util.loggedInUser();
        if (util.isAdmin(user)) {
            return salesRepository.findSalesWithinLastWeek();
        }
        throw new UnauthorisedAccessException(" You're not authorised to access this data");
    }

    @Override
    public int getTotalNumberOfSales() {
        User user = util.loggedInUser();
        if (util.isAdmin(user)) {
            return salesRepository.findAll().size();
        }
        throw new UnauthorisedAccessException(" You're not authorised to access this data");
    }

    @Override
    public String getTotalRevenue() {
        User user = util.loggedInUser();
        if (util.isAdmin(user)) {
            List<Sales> salesList = salesRepository.findAll();
            double totalRevenue = 0.0;
            for (Sales sales : salesList) {
                totalRevenue = +Double.parseDouble(sales.getTotal());
            }
            return String.valueOf(totalRevenue);
        }
        throw new UnauthorisedAccessException(" You're not authorised to access this data");
    }

    @Override
    public List<ProductDetailResponse> topSellingProduct() {
        User user = util.loggedInUser();
        if (util.isAdmin(user)) {
            List<Long> productIds = salesRepository.findTop5ProductsBySales();
            return productIds.stream()
                    .map(util::getProduct)
                    .map(util::getProductDetail)
                    .toList();
        }
        throw new UnauthorisedAccessException(" You're not authorised to access this data");
    }

    @Override
    public List<ClientDetailResponse> topPerformingSalesPerson() {
        User user = util.loggedInUser();
        if (util.isAdmin(user)) {
            List<Long> sellerIDs = salesRepository.findSellerWithHighestSales();
            return sellerIDs.stream()
                    .map(util::getSeller)
                    .map(util::getClientDetails)
                    .toList();
        }
        throw new UnauthorisedAccessException(" You're not authorised to access this data");
    }

    @Override
    public int getTotalNumberOfClients() {
        User user = util.loggedInUser();
        if (util.isAdmin(user)) {
            List<User> clientsList = userRepository.findAllClients();
            return clientsList.size();
        }
        throw new UnauthorisedAccessException(" You're not authorised to access this data");
    }

    @Override
    public List<ClientDetailResponse> topBuyingClient() {
        User user = util.loggedInUser();
        if (util.isAdmin(user)) {
            List<Long> clientsIDs = salesRepository.findHighestBuyingClient();
            return clientsIDs.stream()
                    .map(util::getClient)
                    .map(util::getClientDetails)
                    .toList();
        }
        throw new UnauthorisedAccessException(" You're not authorised to access this data");
    }

    @Override
    public List<ClientDetailResponse> clientFromSimilarLocation() {
        User user = util.loggedInUser();
        if (util.isAdmin(user)) {
            List<User> clientList = userRepository.findClientsWithSimilarAddress();
            return clientList.stream()
                    .map(util::getClientDetails)
                    .toList();
        }
        throw new UnauthorisedAccessException(" You're not authorised to access this data");
    }
}
