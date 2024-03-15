package com.salsa.lottery.dto.request.user;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserCreateRequest {
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
    //    is it necessary? no, bcs user don't need to input transactionId when they create account
//    private String transaction;
}
