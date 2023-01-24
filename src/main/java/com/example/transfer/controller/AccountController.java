package com.example.transfer.controller;

import com.example.transfer.model.dto.AccountDTO;
import com.example.transfer.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping()
    public ResponseEntity<AccountDTO> saveAccount(@RequestBody AccountDTO account){
        return new ResponseEntity<>(accountService.saveAccount(account), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable("id") long AccountId){
        return new ResponseEntity<>(accountService.getAccountById(AccountId), HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<AccountDTO> updateAccount(@PathVariable("id") long id, @RequestBody AccountDTO Account){
        return new ResponseEntity<>(accountService.updateAccount(Account, id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable("id") long id){

        accountService.deleteAccount(id);

        return new ResponseEntity<>("Account deleted successfully!.", HttpStatus.OK);
    }
}
