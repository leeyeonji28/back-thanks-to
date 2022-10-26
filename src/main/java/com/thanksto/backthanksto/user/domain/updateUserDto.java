package com.thanksto.backthanksto.user.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class updateUserDto {
    private long id;
    private String username;
    private String password;
    private String nickName;
    private String userSay;
    private String profileImg;
}
