package com.lin.licslan.mysql.service;


import com.lin.licslan.mysql.entity.User;
import com.lin.licslan.mysql.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CrudService {


    private final UserMapper userMapper;

    public CrudService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public List<User> findAll() {
        return userMapper.findAll();
    }


    public User findOne(Long id) {
        return userMapper.findById(id);
    }


    public Boolean delOne(Long id) {
        return userMapper.delOne(id);
    }

    /**
     * 事物传播机制 事物失效 事务类型等... 可以展开理解一下
     * */
    @Transactional
    public int updateOrAdd(User user) {

        if (user.getId() != null) {
            return userMapper.update(user);
        }
        return userMapper.add(user);
    }
}
