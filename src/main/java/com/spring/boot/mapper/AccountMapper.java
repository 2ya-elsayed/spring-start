package com.spring.boot.mapper;

import com.spring.boot.dto.AccountDto;
import com.spring.boot.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.lang.annotation.Target;
import java.util.List;

@Mapper
public interface AccountMapper {
    AccountMapper ACCOUNT_MAPPER = Mappers.getMapper(AccountMapper.class);

    Account toAccount(AccountDto accountDto);

    @Mapping(target = "password", ignore = true)
    AccountDto toAccountDto(Account account);

    List<Account> toAccounts(List<AccountDto> accountDtos);
    List<AccountDto> toAccountDtos(List<Account> accounts);
}
