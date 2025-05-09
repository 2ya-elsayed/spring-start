package com.spring.boot.service.impl;

import com.spring.boot.dto.AccountDto;
import com.spring.boot.model.Account;
import com.spring.boot.repo.AccountRepo;
import com.spring.boot.service.AccountService;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.spring.boot.mapper.AccountMapper.ACCOUNT_MAPPER;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepo accountRepo;

    @Override
    public List<AccountDto> getAccounts() {
        List<AccountDto> accountDtos = new ArrayList<>();
        List<Account> accounts = accountRepo.findAll();

        return getAccountDtos(accounts, accountDtos);
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) throws SystemException {
        if (Objects.nonNull(accountDto.getId())) {
            throw new SystemException("Id must be null");
        }

        Optional<Account> accountExist = accountRepo.findByUserName(accountDto.getUserName());

        if (accountExist.isPresent()) {
            throw new SystemException("There is already an account with that userName");
        }

        Account account = ACCOUNT_MAPPER.toAccount(accountDto);
        accountRepo.save(account);

        return accountDto;
    }

    @Override
    public AccountDto updateAccount(AccountDto accountDto) throws SystemException {
        if (Objects.isNull(accountDto.getId())) {
            throw new SystemException("Id mustn't be null");
        }

        checkAccountExist(accountDto.getId());

        Account account = ACCOUNT_MAPPER.toAccount(accountDto);
        accountRepo.save(account);

        return accountDto;
    }

    @Override
    public boolean deleteAccount(Long id) throws SystemException {
        Optional<Account> accountExist = accountRepo.findById(id);
        if (accountExist.isEmpty()) {
            return false;
        }
        accountRepo.deleteById(id);
        return true;
    }

    @Override
    public List<AccountDto> seacrh(String value) throws SystemException {
        if (Objects.isNull(value)) {
            throw new SystemException("Search value can't be null");
        }

        List<AccountDto> accountDtos = new ArrayList<>();
        List<Account> accounts = accountRepo.findByUserNameContainingIgnoreCase(value);

        return getAccountDtos(accounts, accountDtos);
    }

    private List<AccountDto> getAccountDtos(List<Account> accounts, List<AccountDto> accountDtos) {
        for (Account ac : accounts){
            AccountDto accountDto = ACCOUNT_MAPPER.toAccountDto(ac);
            accountDtos.add(accountDto);
        }

        return accountDtos;
    }

    private void checkAccountExist(Long id) throws SystemException {
        Optional<Account> accountExist = accountRepo.findById(id);

        if (accountExist.isEmpty()) {
            throw new SystemException("Can't find account with id " + id);
        }
    }
}
