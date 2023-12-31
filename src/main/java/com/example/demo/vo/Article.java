package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    private int id;
    private String regDate;
    private String updateDate;
    private String title;
    private String body;
    private int memberId;
    private String writerName;
    private int views;
    private int goodReactionPoint;
    private int badReactionPoint;
    private int sumReactionPoint;

    public String getForPrintBody(){
        return this.body.replaceAll("\n", "<br/>");
    }
}
