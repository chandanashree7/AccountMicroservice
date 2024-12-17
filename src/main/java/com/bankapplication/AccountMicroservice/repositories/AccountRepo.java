package com.bankapplication.AccountMicroservice.repositories;

import com.bankapplication.AccountMicroservice.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface AccountRepo extends JpaRepository<Account, Serializable> {

}
