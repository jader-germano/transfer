package com.example.transfer.controller;

import com.example.transfer.model.dto.TransferRequestDTO;
import com.example.transfer.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transfer")
public class TransferController {

    @Autowired
    private TransferService transferService;

    @PostMapping
    public ResponseEntity<TransferRequestDTO> transferAmount(@RequestBody TransferRequestDTO transferRequestDTO){
        return new ResponseEntity<>(transferService.transfer(transferRequestDTO), HttpStatus.OK);
    }
}
