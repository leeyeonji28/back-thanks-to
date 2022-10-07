package com.thanksto.backthanksto.user.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.thanksto.backthanksto.post.domain.Post;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String username;
    private String password;

    @Column(unique = true, length = 50)
    private String nickName;

    @Column(length = 200)
    private String userSay;

    private String profileImg;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Post> postList;

    private String roles;
    public List<String> getRoleList(){
        if (this.roles.length() > 0){
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }


}
