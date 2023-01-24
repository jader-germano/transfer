package com.example.transfer.repository;

import com.example.transfer.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t " +
            "LEFT JOIN Fetch t.account a " +
            "LEFT JOIN Fetch a.user u " +
            "Where u.id = :userId " +
            "ORDER BY t.date DESC")
    List<Transaction> findByAccountIdAndUserId(@Param("userId") Long userId);

}
