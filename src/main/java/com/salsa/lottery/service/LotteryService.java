package com.salsa.lottery.service;

import com.salsa.lottery.dto.request.lottery.LotteryRequest;
import com.salsa.lottery.dto.request.user.UserCreateRequest;
import com.salsa.lottery.dto.request.user.UserSearchRequest;
import com.salsa.lottery.dto.response.ControllerResponse;
import com.salsa.lottery.entity.Lottery;
import com.salsa.lottery.entity.User;
import org.springframework.data.domain.Pageable;

public interface LotteryService {
    ControllerResponse<?> createNewLottery(LotteryRequest request);
    ControllerResponse<?> getAllLotteryWithPage(Pageable pageable, LotteryRequest request);
    Lottery getLotteryById(String id);
}
