package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    private int id;
    private LocalDateTime redDate;
    private LocalDateTime updateDate;
    private String title;
    private String body;
}
