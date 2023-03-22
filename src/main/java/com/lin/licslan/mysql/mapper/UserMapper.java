package com.lin.licslan.mysql.mapper;

import com.lin.licslan.mysql.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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
}
