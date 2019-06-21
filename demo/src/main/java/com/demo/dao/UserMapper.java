package com.demo.dao;


import java.util.List;

import com.demo.entity.User;


public interface UserMapper {
    int deleteByPrimaryKey(Integer id);
    int insert(User record);
    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);
    List<User> selectUser();

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}
