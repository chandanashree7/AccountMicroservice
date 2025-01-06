package com.bankapplication.accounts.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data

public class UserProfile implements Serializable {

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
