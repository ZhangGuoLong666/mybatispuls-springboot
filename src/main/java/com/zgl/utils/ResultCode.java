package com.zgl.utils;

public class ResultCode {
    //未登录
    public static final Integer UNLOGING_PLEASE_LOGING = 1001;
    //账号或者密码错误
    public static final Integer USERNAME_OR_PASSWORD_ERROR = 1002;
    //用户不存在
    public static final Integer USER_NOT_EXIT = 1003;
    //没有检查到token
    public static final Integer NO_TOKE_PLEASE_LOGIN_TRY_AGAIN = 1004;
    //数据库里没有对应的token
    public static final Integer  DATABASE_NOT_HAVE_TOKEN_LOGIN_REQUIRED=1005;
    //用户token与数据库token不匹配
    public static final Integer YOUR_TOKEN_MODIFIED_LOGIN_REQUIRED=1006;
    //你的oekn已经过期请重新登陆
    public static final Integer TOUR_TOKEN_HAS_EXPIRED_LOGIN_REQUIED=1007;
    //token异常请重新登陆
    public static final Integer  TOEKN_EXCPTION_PLEASE_LOGNIN_AGAIN=1008;
}
