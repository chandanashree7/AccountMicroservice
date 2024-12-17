package com.bankapplication.AccountMicroservice.controller;

import com.bankapplication.AccountMicroservice.Services.Interfaces.AccountService;
import com.bankapplication.AccountMicroservice.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/account")
    public ResponseEntity<String> createAccount(@RequestBody Account account){
        String status=accountService.upsert(account);
        return new ResponseEntity<>(status, HttpStatus.CREATED);

    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<Account> getAccount(@PathVariable Long accountId){
        Account account=accountService.getByAccountId(accountId);
        return new ResponseEntity<>(account,HttpStatus.OK);
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAllAccounts(){
        List<Account> allAccounts=accountService.getAllAccount();
        return new ResponseEntity<>(allAccounts,HttpStatus.OK);
    }

    @PutMapping("/account")
    public ResponseEntity<String> updateAccount(@RequestBody Account account){
        String status=accountService.upsert(account);
        return new ResponseEntity<String>(status, HttpStatus.OK);
    }

    @DeleteMapping("/account/{accountId}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long accountId){
        String status=accountService.deleteById(accountId);
        return new ResponseEntity<>(status,HttpStatus.OK);

    }

}


