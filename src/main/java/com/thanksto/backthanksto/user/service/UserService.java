package com.thanksto.backthanksto.user.service;

import com.thanksto.backthanksto.user.dao.UserRepository;
import com.thanksto.backthanksto.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<User> getUserData(Long id) {
        return this.userRepository.findById(id);
    }

//    public void modifyUser(Long id) {
//        Optional<User> userModify = this.userRepository.findById(id);
//        userModify.username = userModify.
//
////                user.setUsername(user.getUsername());
////        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
////        user.setRoles("ROLE_USER");
////        user.setNickName(user.getNickName());
////        user.setUserSay(user.getUserSay());
////        user.setProfileImg(fileUrl);
//        this.userRepository.save(userModify);
//    }
}
