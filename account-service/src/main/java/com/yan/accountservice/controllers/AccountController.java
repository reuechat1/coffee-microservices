package com.yan.accountservice.controllers;

import com.yan.accountservice.dto.ClaimsResponse;
import com.yan.accountservice.dto.UserDto;
import com.yan.accountservice.models.User;
import com.yan.accountservice.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
@AllArgsConstructor
public class AccountController {

    private final UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody UserDto dto){
        return userService.createUser(dto);
    }

    @GetMapping("/get_user_claims")
    public ClaimsResponse sendClaimsToAuthService(@RequestBody UserDto dto){
        return userService.sendClaimsToAuthService(dto);
    }
}
