package com.example.demo.dao;

import com.example.demo.vo.Board;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface BoardDao {
    @Select("""
             SELECT * 
                FROM board
                WHERE id = #{boardId}
            """)
    Board getBoardById(int boardId);
}
