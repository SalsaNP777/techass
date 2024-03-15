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
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public ControllerResponse<?> createUser(UserCreateRequest request) {
        User user = User.builder()
                .userName(request.getName())
                .userEmail(request.getEmail())
                .userAddress(request.getAddress())
                .phoneNumber(request.getPhoneNumber())
                .build();
        userRepository.save(user);

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

//    TOLONG SAYA KEVIN, INI RESPONSENYA KAYA GIMANA!!
    @Override
    public ControllerResponse<?> getAllUserWithPage(Pageable pageable, UserSearchRequest request) {
        Specification<User> specification = UserSpecification.getSpecification(request);
        Page<User> page = userRepository.findAll(specification, pageable);

        //buat List baru buat nampung isi semua data nya ya :3
        List<UserResponse> userResponseList = new ArrayList<>();
        // Looping isi dari page yang hasil dari findAll
        for (User user : page){
            //Langsung bungkus user nya ke dalem response
            UserResponse response = UserResponse.builder()
                    .id(user.getId())
                    .address(user.getUserAddress())
                    .email(user.getUserEmail())
                    .name(user.getUserName())
                    .phoneNumber(user.getPhoneNumber())
                    .build();

            //kemudian kan itu dia udah jadi response
            //masing-masing response yang udah kebuat masukin ke dalem list yang tadi buat nampung

            userResponseList.add(response);

            // lakukan di dalem looping biar di add terus ampe semua dah dapet.
        }

        //jadi udah ga butuh ini
//        UserResponse userResponse = UserResponse.builder()
//                .id(user.getId())
//                .name(user.getUserName())
//                .email(user.getUserEmail())
//                .address(user.getUserAddress())
//                .phoneNumber(user.getPhoneNumber())
//                .build();

        PageResponseWrapper pageResponseWrapper = PageResponseWrapper.builder()
                .data(userResponseList)
                .totalElement(page.getTotalElements())
                .totalPage(page.getTotalPages())
                .currentPage(page.getNumber())
                .size(page.getSize())
                .build();

        ControllerResponse<?> response = ControllerResponse.<PageResponseWrapper>builder()
                .message("Users List")
                .data(pageResponseWrapper)
                .build();

        return response;
    }

//    @Override
//    public List<User> getAllUser(UserSearchRequest request) {
//        Specification<User> specification = UserSpecification.getSpecification(request);
//        return userRepository.findAll();
//    }

}
