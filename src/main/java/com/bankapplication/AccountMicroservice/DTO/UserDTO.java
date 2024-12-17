package com.bankapplication.AccountMicroservice.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDTO {

    private Long userId;
    private String username;
    private String email;
    private String passwordHash;
    private String phoneNumber;
    private boolean twoFactorEnabled=false;
    private String kycStatus="pending";
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;



}
