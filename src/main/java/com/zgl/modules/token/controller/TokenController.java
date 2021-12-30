package com.zgl.modules.token.controller;

import com.zgl.modules.token.entity.TokenEntity;
import com.zgl.modules.token.service.TokenService;
import com.zgl.modules.user.entity.User;
import com.zgl.modules.user.service.UserService;
import com.zgl.utils.R;
import com.zgl.utils.ResultCode;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
@Api(tags = "验证操作")
@Controller
//可以换成@RestController
@RequestMapping("/API/")
//方便拦截API路径下的URL
public class TokenController {
    @Resource
    private UserService userService;
    @Resource
    private TokenService tokenService;
    @PostMapping("/index")
    @ApiOperation(value = "test未放行")
    public String showindex() {
        return "index";
    }
    @PostMapping("/login")
    @ApiOperation(value = "test放行")
    public String showlogin() {
        return "login";
    }
    @ResponseBody
    @PostMapping("/auth")
    @ApiOperation(value = "登陆校验")
    public R yanzheng(@RequestBody User resqUser, HttpServletRequest request) {
        //创建返回信息对象

        //判断用户信息为空
        if ("".equals(resqUser.getUsername()) || "".equals(resqUser.getPassword())) {

            return R.error(ResultCode.UNLOGING_PLEASE_LOGING,"请登录后重试",false);
        }

        //根据客户用户名查找数据库的usre对象
        User myUser = (User) userService.getOneByName(resqUser.getUsername()).getData();
        //判断用户不存在
        if (null == myUser) {
            return  R.error(ResultCode.USER_NOT_EXIT,"用户不存在",false);
        }
        //判断用户不存在
        if (!resqUser.getPassword().equals(myUser.getPassword())) {
            return  R.error(ResultCode.USERNAME_OR_PASSWORD_ERROR,"账号或者密码错误",false);
        }

        //根据数据库的用户信息查询Token
        TokenEntity token = (TokenEntity) tokenService.findByUserId(myUser.getId()).getData();
        //为生成Token准备
        String TokenStr = "";
        Date date = new Date();
        int nowtime = (int) (date.getTime() / 1000);
        //生成Token
        TokenStr = creatToken(myUser, date);
        if (null == token) {
            //第一次登陆
            token = new TokenEntity();
            token.setToken(TokenStr);
            token.setBulidtime(nowtime);
            token.setUserId(myUser.getId());
            tokenService.addToken(token);
        }else{
            //登陆就更新Token信息
            TokenStr = creatToken(myUser, date);
            token.setToken(TokenStr);
            token.setBulidtime(nowtime);
            tokenService.updataToken(token);
        }
        //返回Token信息给客户端
        return  R.success(true);
    }
    //生成Token信息方法（根据有效的用户信息）
    private String creatToken(User user, Date date) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT") // 设置header
                .setHeaderParam("alg", "HS256").setIssuedAt(date) // 设置签发时间
                .setExpiration(new Date(date.getTime() + 1000 * 60 * 60 * 24 * 3))
                .claim("userid",String.valueOf(user.getId()) ) // 设置内容
                .setIssuer("zgl")// 设置签发人
                .signWith(signatureAlgorithm, "zgl-key"); // 签名，需要算法和key
        String jwt = builder.compact();
        return jwt;
    }
}
