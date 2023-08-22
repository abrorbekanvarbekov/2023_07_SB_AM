package com.example.demo.dao;

import com.example.demo.vo.Article;
import com.example.demo.vo.Reply;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface ReplyDao {
    @Insert("""
            insert into reply
            set regDate = now(),
                updateDate = now(),
                memberId = #{loginedMemberId},
                relTypeCode = #{relTypeCode},
                articleId = #{articleId},
                `body` = #{replyBody}
            """)
    void writeReply(int loginedMemberId, int articleId, String relTypeCode, String replyBody);

    @Select("""
            select r.*, m.nickname as writerName
                 from reply as r
                 inner join member as m
                 on r.memberId = m.id
                 where r.articleId = #{articleId}
            """)
    List<Reply> getReplyList(int articleId);

}
