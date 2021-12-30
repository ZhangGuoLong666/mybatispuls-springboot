package com.zgl.modules.user.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zgl.modules.user.entity.User;
import com.zgl.modules.user.mapper.UserMapper;
import com.zgl.modules.user.service.UserService;
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
    @Override
    public R getOneByName(String name) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>() ;
        queryWrapper.eq("username",name);
        User user=userMapper.selectOne(queryWrapper);
        return R.success(user);
    }
}
