package com.rena.simplemanagementsystem.service.serviceImpl;

import com.rena.simplemanagementsystem.Exception.EntityAlreadyExistException;
import com.rena.simplemanagementsystem.Exception.EntityNotFoundException;
import com.rena.simplemanagementsystem.Exception.PasswordMismatchException;
import com.rena.simplemanagementsystem.Repository.UserRepository;
import com.rena.simplemanagementsystem.config.authentication.JwtService;
import com.rena.simplemanagementsystem.dto.request.ClientDetailRequest;
import com.rena.simplemanagementsystem.dto.request.SignInRequest;
import com.rena.simplemanagementsystem.dto.response.ClientDetailResponse;
import com.rena.simplemanagementsystem.dto.response.SignInResponse;
import com.rena.simplemanagementsystem.enums.Role;
import com.rena.simplemanagementsystem.model.User;
import com.rena.simplemanagementsystem.service.AuthenticationService;
import com.rena.simplemanagementsystem.util.ApplicationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private  final ApplicationUtil util;

    @Override
    public ClientDetailResponse createClient(ClientDetailRequest request)
    {
        User user = userRepository.findUserByEmail(request.getEmail());
        if(user != null){
            log.info("===={}", user);
            throw new EntityAlreadyExistException("User "+ request.getEmail() + "Already Exist");
        }
        User newUser = getUser(request);
        newUser.setRole(Role.CLIENT);
        userRepository.save(newUser);

        newUser.setCreatedBy(newUser.getFirstName() +" " + newUser.getLastName());
        userRepository.save(newUser);

        return util.getClientDetails(newUser);
    }

    @Override
    public SignInResponse authenticateClient(SignInRequest request)
    {
      User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() ->
              new EntityNotFoundException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new PasswordMismatchException("Incorrect password");

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var jwt = jwtService.generateToken(user);

        SignInResponse response = new SignInResponse();
        response.setMessage("Log in successful");
        response.setToken(jwt);

        return response;
    }


    private User getUser(ClientDetailRequest request)
    {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setAddress(request.getAddress());
        user.setMobileNumber(request.getMobileNumber());
        return user;
    }
}
