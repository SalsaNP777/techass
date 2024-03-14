package com.salsa.lottery.repository;

import com.salsa.lottery.entity.User;
import com.salsa.lottery.entity.UserLottery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLotteryRepository extends JpaRepository<UserLottery, String> {
}
