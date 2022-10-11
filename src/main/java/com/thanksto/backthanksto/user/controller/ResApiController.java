package com.thanksto.backthanksto.user.controller;

import com.thanksto.backthanksto.user.dao.UserRepository;
import com.thanksto.backthanksto.user.domain.User;
import com.thanksto.backthanksto.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
//@CrossOrigin(origins = "http://localhost:3000")
public class ResApiController {
    private final UserRepository userRepository;
    private final  BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserService userService;

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @PostMapping("/token")
    public String token() {
        return "token";
    }

    @PostMapping("/api/join")
    public String join(@RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles("ROLE_USER");
        user.setUserSay(user.getUserSay());
        user.setProfileImg(user.getProfileImg());
        userRepository.save(user);
        return "회원가입 완료";
    }

    @GetMapping("/api/user/{id}")
    public Optional<User> getUser(@PathVariable Long id) {
        return this.userService.getUserData(id);
    }

    // user, manager, admin 접근 가능
    @GetMapping("/api/v1/user")
    public String user(){
        return "user";
    }

    // manager, admin 접근 가능
    @GetMapping("/api/v1/manager")
    public String manager(){
        return "manager";
    }

    // admin 접근 가능
    @GetMapping("/api/v1/admin")
    public String admin(){
        return "admin";
    }
}
