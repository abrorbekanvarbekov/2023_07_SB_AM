package com.example.demo.dao;

import com.example.demo.vo.ReactionPoint;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface ReactionPointDao {
    @Select("""
            select ifnull(sum(`point`), 0) as point
                 from reactionPoint
                 where memberId = #{loginedMemberId}
                 and relId = #{relId}
                 and relTypeCode = #{relTypeCode}
            """)
    ReactionPoint getReactionPoint(int loginedMemberId, String relTypeCode, int relId);

    @Insert("""
                insert into reactionPoint
                     set regDate     = now(),
                         updateDate  = now(),
                         memberId    = #{loginedMemberId},
                         relTypeCode = #{relTypeCode},
                         relId       = #{relId},
                         `point`     = #{point};
            """)
    void doInsertReactionPoint(int loginedMemberId, String relTypeCode, int relId, int point);

    @Delete("""
            delete from reactionPoint
            where memberId = #{loginedMemberId}
            and relTypeCode = #{relTypeCode}
            and relId = #{relId}
            """)
    void doDeleteReactionPoint(int loginedMemberId, String relTypeCode, int relId);
}
