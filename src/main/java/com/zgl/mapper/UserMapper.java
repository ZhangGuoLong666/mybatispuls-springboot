package com.zgl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zgl.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
