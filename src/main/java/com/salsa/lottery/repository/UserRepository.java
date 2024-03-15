package com.salsa.lottery.repository;

import com.salsa.lottery.dto.request.user.UserCreateRequest;
import com.salsa.lottery.entity.User;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.NamedQuery;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.lang.annotation.Native;

public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

    @Transactional
    @Modifying
    @Query(
            value = "insert into user (id, name, email, address, phone_number) values (:id, :name, :email, :address, :phoneNumber)",
            nativeQuery = true
    )
    int CreateNewUser(@Param("id") String id, @Param("name") String name, @Param("email") String email,
                       @Param("address") String address, @Param("phoneNumber") String phoneNumber);
}
