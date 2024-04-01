package com.rena.simplemanagementsystem.controller;

import com.rena.simplemanagementsystem.dto.request.ClientDetailRequest;
import com.rena.simplemanagementsystem.dto.response.ClientDetailResponse;
import com.rena.simplemanagementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping("/update")
    public ResponseEntity<ClientDetailResponse> updateClient(@RequestBody ClientDetailRequest request)
    {
        return ResponseEntity.ok(userService.updateClientDetail(request));
    }


    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable Long id)
    {
        return ResponseEntity.ok(userService.deleteClient(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDetailResponse> getAClient(@PathVariable Long id)
    {
        return ResponseEntity.ok(userService.getAClient(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ClientDetailResponse>>  allUsers(Pageable pageable)
    {
       return ResponseEntity.ok(userService.allClient(pageable)) ;
    }
}
