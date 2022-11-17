package com.thanksto.backthanksto.user.controller;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.thanksto.backthanksto.user.dao.UserRepository;
import com.thanksto.backthanksto.user.domain.User;
import com.thanksto.backthanksto.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
//@CrossOrigin(origins = "http://localhost:3000")
public class ResApiController {
    private final UserRepository userRepository;
    private final  BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserService userService;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    private final AmazonS3 amazonS3;

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @PostMapping("/token")
    public String token() {
        return "token";
    }

    // 회원가입
    @PostMapping(value = "/api/join", consumes = {"multipart/form-data"})
    public String join(@RequestPart(value = "user", required=false) User user,
                       @RequestPart(value = "profileImg") MultipartFile profileImg) throws IOException {

//        String profilePath = System.getProperty("user.dir") + "/src/main/resources/static/"; // 저장 경로
//
//
//        if (!new File(profilePath).exists()){
//            try{
//                new File(profilePath).mkdirs();
//            } catch (Exception e){
//                e.printStackTrace();
//            }
//        }

//        String profileName = profileImg.getOriginalFilename(); // 원래 file 이름
//        UUID uuid = UUID.randomUUID(); // file 이름이 겹치지 않기 위해 uuid 사용
//        String saveFileName = uuid + "_" + profileName; // uuid와 file 이름을 합쳐서 저장
//        String filePath = profilePath + File.separator + saveFileName;
//        profileImg.transferTo(new File(filePath));
//        String fileUrl = "http://localhost:8092/" + saveFileName;

        String s3FileName = String.valueOf(UUID.randomUUID()); // random uuid
        ObjectMetadata objMeta = new ObjectMetadata(); // 버킷에 첨부해주는 역할
        objMeta.setContentLength(profileImg.getInputStream().available()); // 이렇게 넣어야 함

        amazonS3.putObject(bucket, s3FileName, profileImg.getInputStream(), objMeta);

        String fileUrl = "https://thanks-to-site.s3.ap-northeast-2.amazonaws.com/" + s3FileName;

        user.setUsername(user.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles("ROLE_USER");
        user.setNickName(user.getNickName());
        user.setUserSay(user.getUserSay());
        user.setProfileImg(fileUrl);
        userRepository.save(user);
        return "회원가입 완료";
    }

    // 유저 조회
    @GetMapping("/api/user/{id}")
    public Optional<User> getUser(@PathVariable Long id) {
        return this.userService.getUserData(id);
    }

    // 유저 수정
//    @PostMapping(value = "/api/user/{id}/modify")
//    public String modifyUser(updateUserDto updateUserDto, @PathVariable Long id){
//        Optional<User> user = this.userService.getUserData(id); // 유저 불러오기
//
//        updateUserDto.setId(id);
//        updateUserDto.setPassword(bCryptPasswordEncoder.encode(user.get().getPassword()));
////        updateUserDto.setRoles("ROLE_USER");
//        updateUserDto.setUsername(user.get().getUsername());
//        updateUserDto.setNickName(user.get().getNickName());
//        updateUserDto.setUserSay(user.get().getUserSay());
//        updateUserDto.setProfileImg(user.get().getProfileImg());
//        userRepository.save();
//
//        return "수정 완료";
//    }

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
