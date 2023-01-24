package com.example.transfer.repository;

import com.example.transfer.model.TransactionRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRequestRepository extends JpaRepository<TransactionRequest, Long> {

}
