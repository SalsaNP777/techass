package com.salsa.lottery.repository;

import com.salsa.lottery.entity.Lottery;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LotteryRepository extends JpaRepository<Lottery, String>, JpaSpecificationExecutor<Lottery> {
    @Transactional
    @Modifying
    @Query(
            value = "insert into lottery (id, lottery_name) values (:id, :name)",
            nativeQuery = true
    )
    int CreateNewLottery(@Param("id") String id, @Param("name") String name);
}
