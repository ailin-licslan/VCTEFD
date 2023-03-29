package com.lin.licslan.mysql.mapper;

import com.lin.licslan.mysql.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    @Select("select * from t_user")
    List<User> findAll();

    @Select("select * from t_user where id=#{id}")
    User findById(@Param("id") Long id);

    @Select("delete from t_user where id=#{id}")
    Boolean delOne(Long id);

    @Update("update t_user set name=#{name} where id=#{id}")
    int update(User user);

    @Insert({"insert into t_user(name, age) values(#{name}, #{age})"})
    int add(User user);
}
