package com.example.dcmanagesystem.uitls;

import com.example.dcmanagesystem.bean.Admin;
import com.example.dcmanagesystem.service.AdminService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTUtils {
    @Autowired
    AdminService adminService;
    // 过期时间
    private static final long EXPIRE_TIME=60*60*1000;
    // 密钥
    private static final String KEY = "Y2hlbnFxaWFuZ2p1bg==";

    public String createToken(String username){
        Map<String,Object> header = new HashMap<>();
        header.put("typ","JWT");
        header.put("alg", "HS256");
        JwtBuilder builder = Jwts.builder().setHeader(header)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                .setSubject(username)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, KEY);
        return builder.compact();
    }

    /**
     * 验证token是否有效
     * @param token  请求头中携带的token
     * @return  token验证结果  2-token过期；1-token认证通过；0-token认证失败
     */
    public int verify(String token){
        Claims claims = null;
        try {
            //token过期后，会抛出ExpiredJwtException 异常，通过这个来判定token过期，
            claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
        }catch (ExpiredJwtException e){
            return 2;
        }
        //从token中获取用户id，查询该username的用户是否存在，存在则token验证通过
        String username = claims.getSubject();
        Admin admin= adminService.queryAdminByUsername(username);
        if(admin != null){

            return 1;
        }else{
            return 0;
        }
    }
}
