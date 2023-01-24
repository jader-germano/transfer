package com.example.transfer.service;

import com.example.transfer.model.dto.AccountDTO;

import java.util.List;

public interface AccountService {

    AccountDTO saveAccount(AccountDTO accountDTO);
    List<AccountDTO> getAllAccounts();
    AccountDTO getAccountById(Long id);
    AccountDTO updateAccount(AccountDTO user, Long id);
    void deleteAccount(Long id);
}
