package com.zgl.service.impl;


import com.zgl.entity.User;
import com.zgl.mapper.UserMapper;
import com.zgl.service.UserService;
import com.zgl.utils.R;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl  implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public R saveOne(  User user) {
        if(userMapper.insert(user)==1){
            return R.success();
        }else {
            return  R.error();
        }

    }

    @Override
    public R removeOne(Integer id) {
        if(userMapper.deleteById(id)==1){
            return R.success();
        }else {
            return  R.error();
        }
    }

    @Override
    public R modifyOne(User user) {
        if(userMapper.updateById(user)==1){
            return R.success();
        }else {
            return  R.error();
        }
    }

    @Override
    public R getOne(Integer id) {
       User user=userMapper.selectById(id);
       return R.success(user);
    }
}
