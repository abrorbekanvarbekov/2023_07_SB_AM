package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    private int id;
    private String regDate;
    private LocalDateTime updateDate;
    private String loginId;
    private String loginPw;
    private int authLevel;
    private String name;
    private String nickname;
    private String cellphoneNum;
    private String email;
    private int delStatus;
    private LocalDateTime delDate;

}
