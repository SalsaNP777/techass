package com.salsa.lottery.repository;

import com.salsa.lottery.dto.request.user.UserCreateRequest;
import com.salsa.lottery.entity.User;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.lang.annotation.Native;

public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
    @Modifying
    @Query(
            value = "insert into user (name, email, address, phoneNumber) values (:name, :email, :address, :phoneNumber)",
            nativeQuery = true
    )
    void CreateNewUser(@Param("name") String name, @Param("email") String email,
                       @Param("address") String address, @Param("phoneNumber") String phoneNumber);
}
