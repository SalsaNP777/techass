package com.salsa.lottery.controller;

import com.salsa.lottery.dto.request.user.UserCreateRequest;
import com.salsa.lottery.dto.request.user.UserSearchRequest;
import com.salsa.lottery.dto.response.ControllerResponse;
import com.salsa.lottery.dto.response.PageResponseWrapper;
import com.salsa.lottery.entity.User;
import com.salsa.lottery.service.UserService;
import com.salsa.lottery.utils.constant.ApiUrlConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiUrlConstant.USER)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> createUserWithDto(@RequestBody UserCreateRequest request){
        ControllerResponse<?> response = userService.createUser(request);

        ResponseEntity result = ResponseEntity.status(HttpStatus.CREATED)
                .body(response);

        return result;
    }

    @GetMapping
    public ResponseEntity<?> getAllUserWithPage(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "sort-by", defaultValue = "name") String sortBy,
            @RequestParam(name = "direction", defaultValue = "ASC") String direction,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "address", required = false) String address,
            @RequestParam(name = "phone_number", required = false) String phoneNumber
    ){
        UserSearchRequest request = new UserSearchRequest(name, email, address, phoneNumber);
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<User> resultPage = userService.getAllUserWithPage(pageable, request);
        PageResponseWrapper<User> branchPageResponseWrapper =new PageResponseWrapper<>(resultPage);

        return ResponseEntity.status(HttpStatus.OK).body(branchPageResponseWrapper);
    }

//    @GetMapping
//    public List<User> getAllUsers(
//            @RequestParam(name = "name", required = false) String name,
//            @RequestParam(name = "email", required = false) String email,
//            @RequestParam(name = "address", required = false) String address,
//            @RequestParam(name = "phone_number", required = false) String phoneNumber
//    ) {
//        UserSearchRequest request = new UserSearchRequest(name, email, address, phoneNumber);
//        return userService.getAllUser(request);
//    }
}
