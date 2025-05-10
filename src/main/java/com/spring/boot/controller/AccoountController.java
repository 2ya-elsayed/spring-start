package com.spring.boot.controller;

import com.spring.boot.dto.AccountDto;
import com.spring.boot.model.Account;
import com.spring.boot.service.AccountService;
import jakarta.transaction.SystemException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/app/v2")
public class AccoountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/accounts")
    public ResponseEntity<List<AccountDto>> getAccounts() {
        return ResponseEntity.ok(accountService.getAccounts());
    }

    @PostMapping("/addAccount")
    public ResponseEntity<AccountDto> addAccount(@RequestBody @Valid AccountDto accountDto) throws SystemException {
        return ResponseEntity.created(URI.create("/addAccount")).body(accountService.createAccount(accountDto));
    }

    @PutMapping("/updateAccount")
    public ResponseEntity<AccountDto> updateAccount(@RequestBody @Valid AccountDto accountDto) throws SystemException {
        return ResponseEntity.ok().body(accountService.updateAccount(accountDto));
    }

    //    http://localhost:8080/app/v2/deleteAccount?id=1       @RequestParam
    //    http://localhost:8080/app/v2/deleteAccount/1          @PathVariable
    @DeleteMapping("/deleteAccount/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) throws SystemException {
        boolean isDeleted = accountService.deleteAccount(id);
        return isDeleted ?
                ResponseEntity.noContent().build():
                ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<AccountDto>> searchAccount(@RequestParam String value) throws SystemException {
        return ResponseEntity.ok(accountService.seacrh(value));
    }
}
