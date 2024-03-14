package com.salsa.lottery.repository;

import com.salsa.lottery.entity.Lottery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LotteryRepository extends JpaRepository<Lottery, String> {
}
