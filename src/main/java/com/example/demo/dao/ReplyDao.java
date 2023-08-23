package com.example.demo.dao;

import com.example.demo.vo.Reply;
import org.apache.ibatis.annotations.*;

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

    @Delete("""
            delete from reply
            where id = #{id}
            """)
    void doDeleteReply(int id);

    @Select("""
            select * from reply
            where id = #{id}
            """)
    Reply getReplyByArticleId(int id);

    @Update("""
            update reply
            set updateDate = now(),
                `body` = #{body}
            where id = #{id}
            """)
    void doModify(int id, String body);

    @Select("""
            select count(*)
            from reply
            where articleId = #{id}
            """)
    int getReplyCnt(int id);
}
