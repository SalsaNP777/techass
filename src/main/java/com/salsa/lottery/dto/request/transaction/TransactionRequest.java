package com.salsa.lottery.dto.request.transaction;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TransactionRequest {
    private String id;
    private String user;
    private String lottery;
}
