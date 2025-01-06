package com.bankapplication.accounts.repositories;

import com.bankapplication.accounts.dto.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {

    // Find Account by User ID
    Optional<Account> findByUserId(Long userId);
}
