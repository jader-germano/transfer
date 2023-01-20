package com.example.transfer.controller;

import com.example.transfer.model.TransferRequest;
import com.example.transfer.model.dto.TransactionDTO;
import com.example.transfer.model.dto.TransferRequestDTO;
import com.example.transfer.service.TransactionService;
import com.example.transfer.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransferService transferService;

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transfer")
    public ResponseEntity<TransferRequestDTO> transferAmount(@RequestBody TransferRequest transferRequest){
        return new ResponseEntity<>(transferService.transfer(transferRequest), HttpStatus.OK);
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<List<TransactionDTO>> extract(@PathVariable Integer accountNumber) {
        return new ResponseEntity<>(transactionService.extract(accountNumber), HttpStatus.OK);
    }
}
