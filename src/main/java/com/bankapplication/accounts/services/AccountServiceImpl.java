package com.bankapplication.accounts.services;

import com.bankapplication.accounts.model.Accounts;
import com.bankapplication.accounts.model.UserProfile;
import com.bankapplication.accounts.services.Interfaces.AccountService;
import com.bankapplication.accounts.dto.Account;
import com.bankapplication.accounts.repositories.AccountRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Autowired
    private final AccountRepo accountRepo;


    @Autowired
    @Qualifier("restTemplate")
    private RestTemplate restTemplate;
   // private static final Logger logger= LoggerFactory.getLogger(AccountServiceImpl.class);

    public AccountServiceImpl(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepo.findAll();
    }

    @Override
    public Optional<Account> getAccountById(Long accountId) {
        return accountRepo.findById(accountId);
    }

    @Override
    public Account saveAccount(Account account) {
        try {
            return accountRepo.save(account);
        } catch (Exception e) {
            log.error("Error occurred for {} :",account.getAccountNumber());
            throw new RuntimeException("An error occured while saving the entry.",e);
        }

    }

    @Override
    public Account updateAccount(Long accountId, Account account) {
        account.setAccountId(accountId);
        return accountRepo.save(account);
    }

    @Override
    public void deleteByAccountId(Long accountId) {
        accountRepo.deleteById(accountId);
    }

    public Accounts getAccountByUserId(Long accountId) {
        String url = "http://localhost:8082/api/v1/user/{userId}";
        Accounts accountResponse=new Accounts();
        ResponseEntity<UserProfile> responseEntity;
        try {
            Optional<Account> acct= getAccountById(accountId);
            if (acct.isPresent()){
                log.info("Account details: {}", acct.get());
                Long userId=acct.get().getUserId();
                if(userId!=null) {
                    responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, UserProfile.class, userId);
                    log.info("Get Enterprise response status: {}", responseEntity.getStatusCode());
                    accountResponse.setUserProfile(responseEntity.getBody());
                    accountResponse.setAccountId(acct.get().getAccountId());
                    accountResponse.setAccountNumber(acct.get().getAccountNumber());

                }
            }
        } catch (HttpStatusCodeException exception) {
            log.error("Get Enterprise response status : {} & body : {}", exception.getStatusCode(),
                    exception.getResponseBodyAsString());
        } catch (ResourceAccessException resourceAccessException) {
            throw new ResourceAccessException("Internal Server Error");
        }
        return accountResponse;
    }

}
