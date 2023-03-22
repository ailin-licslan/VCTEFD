package com.lin.licslan.mysql.service;


import com.lin.licslan.mysql.entity.User;
import com.lin.licslan.mysql.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrudService {


    private final UserMapper userMapper;

    public CrudService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public List<User> findAll(){
        return userMapper.findAll();
    };

    public User findOne(Long id) {
        return userMapper.findById(id);
    }


    public Boolean delOne(Long id) {
        return userMapper.delOne(id);
    }
}
