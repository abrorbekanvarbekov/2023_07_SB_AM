package com.example.demo.vo;

import lombok.Data;

@Data
public class ResultDate<DT> {
    private String resultCode;
    private String msg;
    private DT data1;

    public static <DT>ResultDate<DT> from (String resultCode, String msg){
        return from(resultCode, msg, null);
    }

    public static <DT>ResultDate<DT> from (String resultCode, String msg, DT data1){
        ResultDate<DT> rd = new ResultDate<>();
        rd.resultCode = resultCode;
        rd.msg = msg;
        rd.data1 = data1;

        return rd;
    }

    public boolean isSuccess(){
        return this.resultCode.startsWith("S-");
    }

    public boolean isFail(){
        return isSuccess() == false;
    }
}