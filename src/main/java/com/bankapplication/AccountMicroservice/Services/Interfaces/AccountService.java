package com.bankapplication.AccountMicroservice.Services.Interfaces;

import com.bankapplication.AccountMicroservice.model.Account;

import java.util.List;

public interface AccountService {

    public String upsert(Account account);
    public Account getByAccountId(Long accountId);
    public List<Account> getAllAccount();
    public String deleteById(Long accountId);

}
