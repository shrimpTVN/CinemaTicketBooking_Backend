package com.cinema.ticketbooking.user.controller;

import com.cinema.ticketbooking.dto.requestDto.LoginRequestDto;
import com.cinema.ticketbooking.dto.requestDto.UserRequestDto;
import com.cinema.ticketbooking.dto.responseDto.UserResponseDto;
import com.cinema.ticketbooking.dto.requestDto.ChangePasswordRequest;
import com.cinema.ticketbooking.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;

    @GetMapping({"","/"})
    public ResponseEntity<List<UserResponseDto>> getAllUsers(){
        List<UserResponseDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Integer id){
        UserResponseDto user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> loginUser(@RequestBody LoginRequestDto loginRequest) {
        UserResponseDto user = userService.Login(loginRequest.email(), loginRequest.password());
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/{id}/change-password")
    public ResponseEntity<String> changePassword(@PathVariable Integer id, @RequestBody ChangePasswordRequest request) {
        userService.changePassword(id, request.email(), request.oldPassword(), request.newPassword());
        return ResponseEntity.ok("Thay đổi mật khẩu thành công");
    }

    @PatchMapping("/{id}/update-status")
    public ResponseEntity<String> updateUserStatus(@PathVariable Integer id, @RequestBody UserRequestDto userRequestDto) {
        userService.updateUserStatus(id, userRequestDto.status());
        return ResponseEntity.ok("Cập nhật trạng thái người dùng thành công");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Integer id, @RequestBody UserRequestDto userRequestDto) {
        UserResponseDto user = userService.updateUser(id, userRequestDto);
        return ResponseEntity.ok(user);
    }

    @PostMapping({"","/"})
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto user) {
        UserResponseDto createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }


}
