package com.bankapplication.accounts.services.Interfaces;

import com.bankapplication.accounts.model.Accounts;
import com.bankapplication.accounts.dto.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    List<Account> getAllAccounts();
    Optional<Account> getAccountById(Long accountId);
    Account saveAccount(Account account);
    Account updateAccount(Long accountId,Account account);
    void deleteByAccountId(Long accountId);
    Accounts getAccountByUserId(Long accountId);
}
