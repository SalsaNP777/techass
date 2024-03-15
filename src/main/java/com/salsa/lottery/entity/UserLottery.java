package com.salsa.lottery.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "t_user_lottery")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserLottery {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "lottery_id", referencedColumnName = "id")
    private Lottery lottery;
}
