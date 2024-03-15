package com.salsa.lottery.service;

import com.salsa.lottery.dto.request.user.UserCreateRequest;
import com.salsa.lottery.dto.request.user.UserSearchRequest;
import com.salsa.lottery.dto.response.ControllerResponse;
import com.salsa.lottery.dto.response.user.UserResponse;
import com.salsa.lottery.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    ControllerResponse<?> createUser(UserCreateRequest request);
    Page<User> getAllUserWithPage(Pageable pageable, UserSearchRequest request);
//    List<User> getAllUser(UserSearchRequest request);
}
