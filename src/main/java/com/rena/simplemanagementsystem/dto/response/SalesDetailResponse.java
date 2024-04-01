package com.rena.simplemanagementsystem.dto.response;

import com.rena.simplemanagementsystem.model.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalesDetailResponse {
    private Long id;
    private LocalDateTime creationDate;
    private User client;
    private User seller;
    private String total;
}
