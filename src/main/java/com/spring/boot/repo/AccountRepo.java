package com.spring.boot.repo;

import com.spring.boot.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {
    Optional<Account> findByUserName(String userName);
    List<Account> findByUserNameContainingIgnoreCase(String value);
}
