package com.zgl.modules.user.service;


import com.zgl.modules.user.entity.User;
import com.zgl.utils.R;

public interface UserService {
    R saveOne(User user);
    R removeOne(Integer id);
    R modifyOne(User user);
    R getOne(Integer id);
    R getOneByName(String name);
}
