package com.zgl.modules.token.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zgl.modules.token.entity.TokenEntity;
import com.zgl.modules.token.mapper.TokenMapper;
import com.zgl.modules.token.service.TokenService;
import com.zgl.modules.user.entity.User;
import com.zgl.utils.R;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TokenServiceImpl implements TokenService {
    @Resource
    TokenMapper tokenMapper;
    @Override
    public R addToken(TokenEntity token) {
        if(tokenMapper.insert(token)==1){
            return R.success();
        }else {
            return  R.error();
        }

    }

    @Override
    public R updataToken(TokenEntity token) {
        if(tokenMapper.updateById(token)==1){
            return R.success();
        }else {
            return  R.error();
        }
    }

    @Override
    public R findByUserId(int userid) {
        QueryWrapper<TokenEntity> queryWrapper = new QueryWrapper<>() ;
        queryWrapper.eq("user_id",userid);
        TokenEntity token=tokenMapper.selectOne(queryWrapper);
        return R.success(token);
    }
}
