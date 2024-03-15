package com.salsa.lottery.service.impl;

import com.salsa.lottery.dto.request.transaction.TransactionRequest;
import com.salsa.lottery.dto.response.ControllerResponse;
import com.salsa.lottery.dto.response.lottery.LotteryResponse;
import com.salsa.lottery.dto.response.transaction.TransactionResponse;
import com.salsa.lottery.entity.Lottery;
import com.salsa.lottery.entity.Transaction;
import com.salsa.lottery.entity.User;
import com.salsa.lottery.repository.TransactionRepository;
import com.salsa.lottery.service.LotteryService;
import com.salsa.lottery.service.TransactionService;
import com.salsa.lottery.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserService userService;
    private final LotteryService lotteryService;

// HERE!!!
    @Override
    public ControllerResponse<?> createNewTransaction(TransactionRequest request) {
        User user = userService.getUserById(request.getUser());
        Lottery lottery = lotteryService.getLotteryById(request.getLottery());

        String id = UUID.randomUUID().toString();
        transactionRepository.CreateNewTransaction(id, request.getLottery(), request.getUser());

        Transaction transaction = transactionRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid Id"));

        TransactionResponse transactionResponse = TransactionResponse.builder()
                .id(transaction.getId())
                .user(transaction.getUser())
                .lottery(transaction.getLottery())
                .build();

        ControllerResponse<LotteryResponse> response = ControllerResponse.<TransactionResponse>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("Transaction Created")
                .data(transactionResponse)
                .build();

        return response;
    }

    @Override
    public ControllerResponse<?> getAllTransactionWithPage(Pageable pageable, TransactionRequest request) {
        return null;
    }
}
