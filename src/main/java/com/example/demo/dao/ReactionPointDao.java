package com.example.demo.dao;

import com.example.demo.vo.ReactionPoint;
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

}
