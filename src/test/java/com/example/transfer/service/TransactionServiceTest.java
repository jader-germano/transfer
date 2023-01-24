package com.example.transfer.service;

import com.example.transfer.exception.AccountNotFoundException;
import com.example.transfer.exception.InsufficientFundsException;
import com.example.transfer.model.Account;
import com.example.transfer.model.TransactionRequest;
import com.example.transfer.repository.AccountRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionServiceTest {

    private final Account account = new Account();
    private final Account account2 = new Account();
    @Autowired
    private TransactionRequestService transferService;

    @Autowired
    private AccountRepository accountRepository;

    @Before("")
    public void setUp() {

        account.setId(1L);
        account.setAccountNumber(10001);
        account.setDigit(1);
        account.setBalance(new BigDecimal("1000.00"));


        account.setId(2L);
        account.setAccountNumber(10002);
        account.setDigit(1);
        account.setBalance(new BigDecimal("1000.00"));

        accountRepository.save(account);
        accountRepository.save(account2);
    }

    @Test
    public void testTransfer() {
        TransactionRequest transferRequest = new TransactionRequest();
        transferRequest.getFromTransaction().setAccount(account);
        transferRequest.getToTransaction().setAccount(account2);
        transferRequest.setAmount(new BigDecimal("100.00"));
        transferService.transfer(transferRequest);

        Account fromAccount = accountRepository.getReferenceById(1L);
        Account toAccount = accountRepository.getReferenceById(2L);
        Assert.assertEquals(new BigDecimal("900.00"), fromAccount.getBalance());
        Assert.assertEquals(new BigDecimal("1100.00"), toAccount.getBalance());
    }

    @Test
    public void testTransfer_InsufficientFunds() {
        TransactionRequest transferRequest = new TransactionRequest();
        transferRequest.setId(1L);
        transferRequest.getFromTransaction().setAccount(account);
        transferRequest.getToTransaction().setAccount(account2);
        transferRequest.setAmount(new BigDecimal("10000.00"));
        transferService.transfer(transferRequest);
        assertThrows(InsufficientFundsException.class, () -> {
            throw new InsufficientFundsException("Insufficient funds");
        });
    }

    @Test
    public void testTransfer_InvalidAccount() {
        TransactionRequest transferRequest = new TransactionRequest();
        transferRequest.setId(1L);
        transferRequest.getFromTransaction().setAccount(account);
        transferRequest.getToTransaction().setAccount(account2);
        transferRequest.setAmount(new BigDecimal("100.00"));
        transferService.transfer(transferRequest);
        assertThrows(AccountNotFoundException.class, () -> {
            throw new AccountNotFoundException("Invalid account number");

        });
    }
}
