package com.salsa.lottery.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "name")
    private String userName;
    @Column(name = "email")
    private String userEmail;
    @Column(name = "address")
    private String userAddress;
    @Column(name = "phone_number")
    private String phoneNumber;
    @OneToMany(mappedBy = "user")
    private List<UserLottery> transaction;
}
