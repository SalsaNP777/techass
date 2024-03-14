package com.salsa.lottery.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "lottery")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Lottery {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "lottery_name")
    private String lotteryName;
    @OneToMany(mappedBy = "lottery")
    private List<UserLottery> transaction;
}
