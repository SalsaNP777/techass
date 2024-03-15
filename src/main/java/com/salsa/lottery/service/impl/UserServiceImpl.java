package com.salsa.lottery.service.impl;

import com.salsa.lottery.dto.request.user.UserCreateRequest;
import com.salsa.lottery.dto.request.user.UserSearchRequest;
import com.salsa.lottery.dto.response.ControllerResponse;
import com.salsa.lottery.dto.response.PageResponseWrapper;
import com.salsa.lottery.dto.response.user.UserResponse;
import com.salsa.lottery.entity.User;
import com.salsa.lottery.repository.UserRepository;
import com.salsa.lottery.service.UserService;
import com.salsa.lottery.utils.specification.UserSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public ControllerResponse<?> createNewUser(UserCreateRequest request) {
        User user = User.builder()
                .userName(request.getName())
                .userEmail(request.getEmail())
                .userAddress(request.getAddress())
                .phoneNumber(request.getPhoneNumber())
                .build();
        userRepository.CreateNewUser(user.getUserName(), user.getUserEmail(), user.getUserAddress(), user.getPhoneNumber());

        UserResponse userResponse = UserResponse.builder()
                .id(user.getId())
                .name(user.getUserName())
                .email(user.getUserEmail())
                .address(user.getUserAddress())
                .phoneNumber(user.getPhoneNumber())
                .build();

        ControllerResponse<UserResponse> response = ControllerResponse.<UserResponse>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("User Created")
                .data(userResponse)
                .build();

        return response;
    }

//    @Override
//    public ControllerResponse<?> createUser(UserCreateRequest request) {
//        User user = User.builder()
//                .userName(request.getName())
//                .userEmail(request.getEmail())
//                .userAddress(request.getAddress())
//                .phoneNumber(request.getPhoneNumber())
//                .build();
//        userRepository.save(user);
//
//        UserResponse userResponse = UserResponse.builder()
//                .id(user.getId())
//                .name(user.getUserName())
//                .email(user.getUserEmail())
//                .address(user.getUserAddress())
//                .phoneNumber(user.getPhoneNumber())
//                .build();
//
//        ControllerResponse<UserResponse> response = ControllerResponse.<UserResponse>builder()
//                .status(HttpStatus.CREATED.getReasonPhrase())
//                .message("User Created")
//                .data(userResponse)
//                .build();
//
//        return response;
//    }

    @Override
    public ControllerResponse<?> getAllUserWithPage(Pageable pageable, UserSearchRequest request) {
        Specification<User> specification = UserSpecification.getSpecification(request);
        Page<User> page = userRepository.findAll(specification, pageable);
        List<UserResponse> userResponseList = new ArrayList<>();

        for (User user : page){
            UserResponse response = UserResponse.builder()
                    .id(user.getId())
                    .address(user.getUserAddress())
                    .email(user.getUserEmail())
                    .name(user.getUserName())
                    .phoneNumber(user.getPhoneNumber())
                    .build();
            userResponseList.add(response);
        }

        PageResponseWrapper pageResponseWrapper = PageResponseWrapper.builder()
                .data(userResponseList)
                .totalElement(page.getTotalElements())
                .totalPage(page.getTotalPages())
                .size(page.getSize())
                .build();

        ControllerResponse<?> response = ControllerResponse.<PageResponseWrapper>builder()
                .message("Users List")
                .data(pageResponseWrapper)
                .build();

        return response;
    }
}