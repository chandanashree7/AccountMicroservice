package com.bankapplication.accounts.dto;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="account")
public class Account {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long accountId;
        private String accountNumber;
        private String accountType;
        private double balance;
        private String currency;
        private LocalDateTime createdAt;
        private Long userId;


        @PrePersist
        public void prePersist() {
                if(createdAt==null)
                 createdAt = LocalDateTime.now(); // Set current date and time before saving
        }

}

