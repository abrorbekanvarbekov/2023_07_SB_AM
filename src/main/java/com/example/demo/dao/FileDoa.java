package com.example.demo.dao;

import com.example.demo.vo.FileVo;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface FileDoa {

    @Insert("""
            insert into `file`
                 set regDate= now(),
                     originName = #{orgName},
                     savedName = #{savedName},
                     savePath = #{savedPath};
                        
            """)
    void insertFileInfo(String orgName, String savedName, String savedPath);

    @Select("""
            select * from `file`
            """)
    List<FileVo> getFiles();

    @Select("""
            select * from `file`
            where id = #{id}
            """)
    FileVo getFileById(int id);
}
