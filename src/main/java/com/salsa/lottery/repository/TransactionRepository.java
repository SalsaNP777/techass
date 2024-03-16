package com.salsa.lottery.repository;

import com.salsa.lottery.entity.Transaction;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransactionRepository extends JpaRepository<Transaction, String>, JpaSpecificationExecutor<Transaction> {
//    @Transactional
//    @Modifying
//    @Query(
//            value = "insert into transaction (id, lottery_id, user_id) values (:id, :lotery, :user)",
//            nativeQuery = true
//    )
//    int CreateNewTransaction(@Param("id") String id, @Param("lottery") String lottery, @Param("user") String user);
}
