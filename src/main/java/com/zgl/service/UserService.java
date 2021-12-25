package com.zgl.service;


import com.zgl.entity.User;
import com.zgl.utils.R;

public interface UserService {
    R saveOne(User user);
    R removeOne(Integer id);
    R modifyOne(User user);
    R getOne(Integer id);
}
