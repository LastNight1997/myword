package myzx.learn.word.dao;

import myzx.learn.word.domain.Word;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface WordMapper {
    @Select("select ID, Word, meaning, lx from user natural join learnt natural join words where username = #{username} order by known/(known+unknown)+(known+unknown) limit 1")
    Word findLearntByName(String username);

    @Select("select ID, Word, meaning, lx from user natural join learnt natural join words where username = #{username} order by rand() limit 1")
    Word findLearntByNameRandom(String username);

    @Select("select ID, Word, meaning, lx from user natural join love natural join words where username = #{username}")
    List<Word> findLoveByName(String username);

    @Insert("insert into learnt(username, ID) values(#{username},#{ID})")
    int addLearnt(@Param("username")String username, @Param("ID")int ID);

    @Update("update learnt set known=known+1 where username=#{username} and ID=#{ID}")
    int knownLearnt(@Param("username")String username, @Param("ID")int ID);

    @Update("update learnt set unknown=unknown+1 where username=#{username} and ID=#{ID}")
    int unknownLearnt(@Param("username")String username, @Param("ID")int ID);

    @Insert("insert into love(username, ID) values(#{username},#{ID})")
    int addLove(@Param("username")String username, @Param("ID")int ID);

    @Delete("DELETE FROM love WHERE username = #{username} and ID = #{ID}")
    int deleteLove(@Param("username")String username, @Param("ID")int ID);

    @Select("select * from words where Word = #{Word}")
    Word findByWord(String Word);

    @Select("SELECT ID, Word, meaning, lx FROM `words` where ID not in (select ID from learnt where username = '${username}') ORDER BY RAND() LIMIT ${num}")
    List<Word> findWordByName(@Param("username")String username, @Param("num")int num);
}
