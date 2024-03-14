package com.salsa.lottery.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "user_lottery")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserLottery {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @OneToMany(mappedBy = "transaction")
    private List<User> user;
    @OneToMany(mappedBy = "transaction")
    private List<Lottery> lottery;
}
