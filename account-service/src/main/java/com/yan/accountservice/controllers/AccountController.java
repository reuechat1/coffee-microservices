package com.yan.accountservice.controllers;

import com.yan.accountservice.dto.ClaimsResponse;
import com.yan.accountservice.dto.UserDto;
import com.yan.accountservice.models.User;
import com.yan.accountservice.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/account")
@AllArgsConstructor
public class AccountController {

    private final UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody UserDto dto){
        log.info("Зарегистрировался еще пользователь");
        return userService.createUser(dto);
    }

    @PostMapping("/get_user_claims")
    public ClaimsResponse sendClaimsToAuthService(@RequestBody UserDto dto){
        return userService.sendClaimsToAuthService(dto);
    }
}
