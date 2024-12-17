package com.bankapplication.AccountMicroservice.Services;

import com.bankapplication.AccountMicroservice.Services.Interfaces.AccountService;
import com.bankapplication.AccountMicroservice.model.Account;
import com.bankapplication.AccountMicroservice.repositories.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepo accountRepo;


    @Override
    public String upsert(Account account) {
        accountRepo.save(account);
        return "Success";
    }

    @Override
    public Account getByAccountId(Long accountId) {
        Optional<Account> findById=accountRepo.findById(accountId);
        if(findById.isPresent()){
            return findById.get();
        }
        return null;
    }

    @Override
    public List<Account> getAllAccount() {
        return accountRepo.findAll();
    }

    @Override
    public String deleteById(Long accountId) {
       if(accountRepo.existsById(accountId)){
           accountRepo.deleteById(accountId);
           return "Delete Success";
       }else {
           return "No Record Found";
       }
    }
}
