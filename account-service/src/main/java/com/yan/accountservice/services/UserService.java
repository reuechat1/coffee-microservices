package com.yan.accountservice.services;

import com.yan.accountservice.dto.ClaimsResponse;
import com.yan.accountservice.dto.UserDto;
import com.yan.accountservice.exceptions.InvalidDataException;
import com.yan.accountservice.exceptions.ResourceAlreadyExistsException;
import com.yan.accountservice.exceptions.ResourceNotFoundException;
import com.yan.accountservice.models.Role;
import com.yan.accountservice.models.User;
import com.yan.accountservice.repositories.RoleRepository;
import com.yan.accountservice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public ClaimsResponse sendClaimsToAuthService(UserDto dto){
        Optional<User> userOptional = userRepository.findByUsername(dto.getUsername());
        if (userOptional.isPresent()){
            User user = userOptional.get();
            if (passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
                Optional<Role> roleOptional = roleRepository.findById(user.getRole().getId());
                if (roleOptional.isPresent()){
                    Role role = roleOptional.get();
                    return new ClaimsResponse(role.getTag());
                }else {
                    throw new ResourceNotFoundException("Роль не найдена");
                }
            } else {
                throw new ResourceNotFoundException("Пароль неверный");
            }
        } else {
            throw new ResourceNotFoundException("Пользователь не найден");
        }
    }

    public User createUser(UserDto dto) {
        if (!isValidPassword(dto.getPassword())) {
            throw new InvalidDataException("Пароль не соответствует требованиям");
        }
        Optional<User> userOptional = userRepository.findByUsername(dto.getUsername());
        if (userOptional.isEmpty()) {
            User user = new User();
            user.setUsername(dto.getUsername());
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
            Optional<Role> roleOptional = roleRepository.findByTag("ROLE_USER");
            if (roleOptional.isPresent()){
                Role role = roleOptional.get();
                user.setRole(role);
            }else {
                throw new ResourceNotFoundException("Роль не найдена");
            }
            return userRepository.save(user);
        } else {
            throw new ResourceAlreadyExistsException("Такой пользователь уже существует");
        }
    }

    private boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[a-zA-Z]).{8,}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
