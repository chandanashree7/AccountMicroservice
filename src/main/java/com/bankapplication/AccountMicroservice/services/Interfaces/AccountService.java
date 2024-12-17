package com.bankapplication.AccountMicroservice.services.Interfaces;

import com.bankapplication.AccountMicroservice.model.Account;
import jakarta.persistence.PrePersist;

import java.time.LocalDateTime;
import java.util.List;

public interface AccountService {

    public String upsert(Account account);
    public Account getByAccountId(Long accountId);
    public List<Account> getAllAccount();
    public String deleteById(Long accountId);
    public Account createAccount(Account account);



}
