package com.spring.boot.service;

import com.spring.boot.dto.AccountDto;
import com.spring.boot.model.Account;
import jakarta.transaction.SystemException;

import java.util.List;

public interface AccountService {
    List<AccountDto> getAccounts();
    AccountDto createAccount(AccountDto accountDto) throws SystemException;
    AccountDto updateAccount(AccountDto accountDto) throws SystemException;
    boolean deleteAccount(Long id) throws SystemException;
    List<AccountDto> seacrh(String value) throws SystemException;

}
