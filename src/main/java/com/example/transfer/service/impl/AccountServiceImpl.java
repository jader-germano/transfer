package com.example.transfer.service.impl;

import com.example.transfer.exception.ResourceNotFoundException;
import com.example.transfer.model.Account;
import com.example.transfer.model.User;
import com.example.transfer.model.dto.AccountDTO;
import com.example.transfer.model.dto.UserDTO;
import com.example.transfer.repository.AccountRepository;
import com.example.transfer.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public AccountDTO saveAccount(AccountDTO accountDTO) {
        Account account = new Account();
        account.setId(accountDTO.getId());

        Random rnd = new Random();
        Integer accountNumber = 100000 + rnd.nextInt(900000);
        Integer digit = rnd.nextInt(2);
        account.setUser(new User());
        account.getUser().setId(accountDTO.getUser().getId());
        account.setAccountNumber(accountNumber);
        account.setDigit(digit);
        account.setBalance(new BigDecimal("0"));
        accountRepository.save(account);

        accountDTO = new AccountDTO(accountRepository.save(account));
        logger.info("Account saved!");
        return accountDTO;
    }

    @Override
    public List<AccountDTO> getAllAccounts() {
        logger.info("Accounts!");
        return accountRepository
                .findAll()
                .stream()
                .map(this::converter)
                .collect(Collectors.toList());

    }

    @Override
    public AccountDTO getAccountById(Long id) {
        Account account = accountRepository.findByIdJoinUser(id).orElseThrow(() ->
                new ResourceNotFoundException("Account", "Id", id));
        return new AccountDTO(account);

    }

    @Override
    public AccountDTO updateAccount(AccountDTO account, Long id) {

        Account existingAccount = accountRepository.findByIdJoinUser(id).orElseThrow(
                () -> new ResourceNotFoundException("Account", "Id", id));

        existingAccount.setBalance(account .getBalance());

        Account accountReturn = accountRepository.save(existingAccount);
        return new AccountDTO(accountReturn);
    }

    @Override
    public void deleteAccount(Long id) {

        accountRepository.findByIdJoinUser(id).orElseThrow(() ->
                new ResourceNotFoundException("Account", "Id", id));
        accountRepository.deleteById(id);
    }

    private AccountDTO converter (Account account) {
        AccountDTO result = new AccountDTO();
        Account accountDatabase = accountRepository.getReferenceById(account.getId());
        result.setId(accountDatabase.getId());
        result.setUser(new UserDTO(accountDatabase.getUser()));
        result.setAccountNumber(accountDatabase.getAccountNumber());
        result.setDigit(accountDatabase.getDigit());
        result.setBalance(accountDatabase.getBalance());
        return result;
    }

}
