package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reply {
    private int id;
    private String regDate;
    private String updateDate;
    private int memberId;
    private int articleId;
    private String relTypeCode;
    private String body;
    private String writerName;

    public String getForPrintBody(){
        return this.body.replaceAll("\n", "<br/>");
    }
}
