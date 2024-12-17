package com.bankapplication.AccountMicroservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="account")
public class Account {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long account_Id;
        private String account_number;
        private String account_type;
        private double balance;
        private String currency;
        private LocalDateTime created_at;


    }

