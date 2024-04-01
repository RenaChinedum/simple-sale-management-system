package com.rena.simplemanagementsystem.service.serviceImpl;

import com.rena.simplemanagementsystem.Exception.EntityNotFoundException;
import com.rena.simplemanagementsystem.Exception.UnauthorisedAccessException;
import com.rena.simplemanagementsystem.Repository.UserRepository;
import com.rena.simplemanagementsystem.dto.request.ClientDetailRequest;
import com.rena.simplemanagementsystem.dto.response.ClientDetailResponse;
import com.rena.simplemanagementsystem.enums.Role;
import com.rena.simplemanagementsystem.model.User;
import com.rena.simplemanagementsystem.service.UserService;
import com.rena.simplemanagementsystem.util.ApplicationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ApplicationUtil util;

    @Override
    public ClientDetailResponse updateClientDetail(ClientDetailRequest request)
    {
        User client = util.loggedInUser();
        util.updateFieldIfNotNullOrEmpty(request.getFirstName(), client::setFirstName);
        util.updateFieldIfNotNullOrEmpty(request.getLastName(), client::setLastName);
        util.updateFieldIfNotNullOrEmpty(request.getEmail(), client::setEmail);
        util.updateFieldIfNotNullOrEmpty(request.getAddress(), client::setAddress);
        util.updateFieldIfNotNullOrEmpty(request.getMobileNumber(), client::setMobileNumber);
        userRepository.save(client);
        return util.getClientDetails(client);
    }



    @Override
    public String deleteClient(Long id)
    {
       User adminUser = util.loggedInUser();
       if(adminUser.getRole().equals(Role.ADMIN)){
           User client = userRepository.findById(id).orElseThrow(()
                   -> new EntityNotFoundException("Client not found"));

           userRepository.delete(client);
           return "Client deleted successfully";
       }
       throw new UnauthorisedAccessException("You're not authorised to delete a client");
    }

    @Override
    public List<ClientDetailResponse> allClient(Pageable pageable)
    {
        List<ClientDetailResponse> clientDetailsList = new ArrayList<>();
        Page<User> clientPage = userRepository.findAll(pageable);
        List<User> clientsList = clientPage.getContent();
        for (User user : clientsList){
            ClientDetailResponse detail = util.getClientDetails(user);
            clientDetailsList.add(detail);
        }
        return clientDetailsList;
    }


    @Override
    public ClientDetailResponse getAClient(Long id)
    {
        User client = userRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Client not found"));
        return util.getClientDetails(client);
    }



}
