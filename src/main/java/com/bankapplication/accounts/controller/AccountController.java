package com.bankapplication.accounts.controller;

import com.bankapplication.accounts.model.Accounts;
import com.bankapplication.accounts.services.Interfaces.AccountService;
import com.bankapplication.accounts.dto.Account;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@AllArgsConstructor
@Slf4j
public class AccountController {
//https://www.youtube.com/watch?v=Wb9OTgi7xKM
    @Autowired
    private final AccountService accountService;

    @Autowired
    public RestTemplate restTemplate;
    //private final String BASE_URL="http://localhost:8081/api/account";

    @GetMapping
    public List<Account> getAllAccounts(){
        log.info("getAllAccounts(-) started");
        return accountService.getAllAccounts();
    }

   @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long accountId){
        return accountService.getAccountById(accountId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public  Account createAccount(@RequestBody Account account){

        return accountService.saveAccount(account);
    }

    @PutMapping("account/{accountId}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long accountId,@RequestBody Account account){
        if(accountService.getAccountById(accountId).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(accountService.updateAccount(accountId,account));

    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<Void> deleteByAccountId(@PathVariable Long accountId) {
        if (accountService.getAccountById(accountId).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        accountService.deleteByAccountId(accountId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("account/{accountId}")
    public ResponseEntity<Accounts> getAccountAndUser(@PathVariable Long accountId){
        log.info("Enter into getAccountAndUser: accountId : " + accountId);

        try {
            Accounts data = accountService.getAccountByUserId(accountId);
            System.out.println(data);
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }




}


