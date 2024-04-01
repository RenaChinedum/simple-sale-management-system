package com.rena.simplemanagementsystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDetailResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String mobileNumber;
}
