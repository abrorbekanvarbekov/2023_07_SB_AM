package com.example.demo.dao;

import com.example.demo.vo.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleDao {

    @Insert("""
            INSERT INTO article 
                SET title = #{title},
                    body = #{body}
            """)
    public Article writeArticle(String title, String body);

    @Select("""
            SELECT * 
                FROM article 
                WHERE id = #{id}
            """)
    public Article getArticleById(int id);

    @Delete("""
            DELETE 
                FROM article 
                WHERE id = #{id}
            """)
    public void deleteArticle(int id);

    @Select("""
            SELECT * 
                FROM article 
                ORDER BY ID desc
            """)
    public List<Article> getArticles();

    @Update("""
            UPDATE article 
                SET updateDate = now(), 
                    title = #{title}, 
                    body = #{body} 
                WHERE id = #{id}
            """)
    public void modifyArticle(int id, String title, String body);
}
