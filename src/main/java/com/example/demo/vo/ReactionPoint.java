package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReactionPoint {
    private int id;
    private String regDate;
    private String updateDate;
    private String relTypeCode;
    private int memberId;
    private int relId;
    private int point;
}
