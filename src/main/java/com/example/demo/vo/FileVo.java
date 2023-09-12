package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileVo {
    private int id;
    private String regDate;
    private String originName;
    private String savedName;
    private String savePath;
}