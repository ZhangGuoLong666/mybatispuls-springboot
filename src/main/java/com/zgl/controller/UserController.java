package com.zgl.controller;

import com.zgl.entity.User;
import com.zgl.service.UserService;
import com.zgl.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(tags = "用户登陆")
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @PostMapping("/saveOne")
    @ApiOperation(value = "增加用户")
    public R saveOne(@RequestBody User user){return userService.saveOne(user);}
    @PostMapping("/removeOne")
    @ApiOperation(value = "删除一个用户")
    public R removeOne(Integer id){return userService.removeOne(id);}
    @PostMapping("/modifyOne")
    @ApiOperation(value = "修改用户")
    public R modifyOne(@RequestBody User user){return userService.modifyOne(user);}
    @PostMapping("/getOne")
    @ApiOperation(value = "查找用户")
    public R getOne(Integer id){return userService.getOne(id);}


}
