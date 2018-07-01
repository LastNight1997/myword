package myzx.learn.word.dao;


import myzx.learn.word.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Component
@Mapper
public interface UserMapper {
    @Insert("insert into user(username, password, email) values(#{username},#{password},#{email})")
    int add(User user);

    @Update("update user set password=#{password},email=#{email},level=#{level},num=#{num},havelearnt=#{havelearnt} where username = #{username}")
    int update(User user);

    @Delete("DELETE FROM user WHERE username = #{name}")
    int deleteByName(String name);

    @Select("select * from user where username = #{name}")
    User findByName(String name);

    @Select("select * from user")
    List<User> findAll();
}
