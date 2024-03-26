package com.vincent.mapper;

import com.vincent.domain.User;

import java.util.List;

public interface UserMapper {
    List<User> selectAll();

    User selectById(Integer id);
    List<User> selectByIdCondition(Integer id);
}
