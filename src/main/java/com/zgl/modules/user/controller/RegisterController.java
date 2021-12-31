package com.zgl.modules.user.controller;

import com.zgl.feign.entity.MailEntity;
import com.zgl.feign.service.FeignMessageService;
import com.zgl.modules.user.DTO.UserDTO;
import com.zgl.modules.user.entity.User;

import com.zgl.modules.user.service.UserService;
import com.zgl.utils.R;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("register")
public class RegisterController {

    @Resource
    private FeignMessageService feignMessageService;
    @Resource
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private UserService userService;
    @PostMapping("/getcode")
    public R getcode(@RequestParam(value = "mail", required = true)String mail){
        int code= (int) ((Math.random() * 9 + 1) * 100000);
        redisTemplate.opsForValue().set("mailcode-"+mail,code,300, TimeUnit.SECONDS);
        String text="你的验证码是【"+code+"】请在5分钟之内登陆";
        MailEntity mailEntity=new MailEntity(mail,"验证码",text);
        return feignMessageService.sendMail(mailEntity);
    }
    @PostMapping("/doregister")
    public R register(@RequestBody UserDTO user){
    String code= redisTemplate.opsForValue().get("mailcode-"+user.getEmail()).toString();
    //校验验证码是否正确
    if(code.equals(user.getCode())){
        User userEntity=new User(null,user.getUsername(),user.getPassword(),user.getEmail(),null);
        return userService.saveOne(userEntity);
    }else{return R.error("验证码错误，请重试");}
    }
}
