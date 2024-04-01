package com.rena.simplemanagementsystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateClientRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String mobileNumber;
}
