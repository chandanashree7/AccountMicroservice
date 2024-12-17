package com.bankapplication.AccountMicroservice.services;

import com.bankapplication.AccountMicroservice.DTO.UserDTO;
import com.bankapplication.AccountMicroservice.UserNotFoundException;
import com.bankapplication.AccountMicroservice.services.Interfaces.AccountService;
import com.bankapplication.AccountMicroservice.model.Account;
import com.bankapplication.AccountMicroservice.repositories.AccountRepo;
import jakarta.persistence.PrePersist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AccountRepo accountRepo;

    private final String USER_URL = "http://localhost:8082/user";


    @Override
    public String upsert(Account account) {
        accountRepo.save(account);
        return "Success";
    }

    @Override
    public Account getByAccountId(Long accountId) {
        Optional<Account> findById = accountRepo.findById(accountId);
        if (findById.isPresent()) {
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
        if (accountRepo.existsById(accountId)) {
            accountRepo.deleteById(accountId);
            return "Delete Success";
        } else {
            return "No Record Found";
        }
    }

    @Override
    public Account createAccount(Account account) {
        ResponseEntity<UserDTO> userResponse = restTemplate.exchange(
                USER_URL + "/" + account.getUserId(),
                HttpMethod.GET,
                null,
                UserDTO.class);
        if (userResponse.getStatusCode() == HttpStatus.OK) {
            return accountRepo.save(account);
        } else {
            throw new UserNotFoundException("User with ID" + account.getUserId() + "not found.");
        }
    }





}
