package com.example.transfer.repository;

import com.example.transfer.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    /**
     * @param accountId
     * @return Optional<Account>
     */
    @Query("SELECT account FROM Account as account " +
            "LEFT JOIN FETCH account.user " +
            "WHERE account.id = :accountId")
    Optional<Account> findByIdJoinUser(Long accountId);
}
