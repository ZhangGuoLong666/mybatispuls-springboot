package com.zgl.filter;

import com.alibaba.fastjson.JSONObject;
import com.zgl.modules.token.entity.TokenEntity;
import com.zgl.modules.token.service.TokenService;
import com.zgl.utils.ResultCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Date;

public class TokenInterceptor implements HandlerInterceptor {
    @Resource
    private TokenService tokenService;
    //从Nacos上获得token过期时长
    @Value(value = "${Token.expiredTime}")
    private int tokenExpiredTime;
    //提供查询
    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {}
    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {}
    @Override
    public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
        //普通路径放行
        if ("/API/yanzheng".equals(arg0.getRequestURI()) || "/API/login".equals(arg0.getRequestURI())) {
            return true;}

        //权限路径拦截
        arg1.setCharacterEncoding("UTF-8");
        arg1.setContentType( "application/json; charset=utf-8");
        PrintWriter resultWriter=arg1.getWriter();
        JSONObject res = new JSONObject();
        final String headerToken=arg0.getHeader("Token");
        //判断请求信息
        if(null==headerToken||headerToken.trim().equals("")){
            res.put( "code", ResultCode.NO_TOKE_PLEASE_LOGIN_TRY_AGAIN );
            res.put( "msg", "没有检查到token，请登录后尝试");
            resultWriter.append(res.toString());
            return false;
        }
        //解析Token信息
        try {
            Claims claims = Jwts.parser().setSigningKey("zgl-key").parseClaimsJws(headerToken).getBody();
            String tokenUserId=(String)claims.get("userid");
            int itokenUserId=Integer.parseInt(tokenUserId);
            //根据客户Token查找数据库Token
            TokenEntity myToken=(TokenEntity) tokenService.findByUserId(itokenUserId).getData();

            //数据库没有Token记录
            if(null==myToken) {
                res.put( "code", ResultCode.DATABASE_NOT_HAVE_TOKEN_LOGIN_REQUIRED );
                res.put( "msg","我没有你的token,需要登录");
                resultWriter.append(res.toString());
                return false;
            }
            //数据库Token与客户Token比较
            if( !headerToken.equals(myToken.getToken()) ){
                res.put( "code", ResultCode.YOUR_TOKEN_MODIFIED_LOGIN_REQUIRED );
                res.put( "msg","你的token修改过,需要登录");
                resultWriter.append(res.toString());
                return false;
            }
            //判断Token过期
            Date tokenDate=(Date)claims.getExpiration();
            int chaoshi=(int)((new Date().getTime()-tokenDate.getTime())+1000 * 60 * 60 * 24 * 3)/1000;
            if(chaoshi>tokenExpiredTime*60*1*1*1){
                res.put( "code", ResultCode.TOUR_TOKEN_HAS_EXPIRED_LOGIN_REQUIED );
                res.put( "msg","你的token过期了,需要登录");
                resultWriter.append(res.toString());
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            res.put( "code", ResultCode.TOEKN_EXCPTION_PLEASE_LOGNIN_AGAIN );
            res.put( "msg","token异常，请重新登陆");
            resultWriter.append(res.toString());
            return false;
        }
        //最后才放行
        return true;
    }
}
