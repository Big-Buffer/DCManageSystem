package com.example.dcmanagesystem.filter;

import com.example.dcmanagesystem.uitls.JWTUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebFilter(filterName = "login", urlPatterns = "/*")
@Order(1)
public class LoginFilter implements Filter {
    @Autowired
    JWTUtils jwtUtils;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Map<String,Object> map = new HashMap<>();
        String url =  ((HttpServletRequest)servletRequest).getRequestURI();
        if(url != null){
            //登录请求直接放行
            if("/login".equals(url)){
                filterChain.doFilter(servletRequest,servletResponse);
                return;
            }else{
                //其他请求验证token
                String token = ((HttpServletRequest)servletRequest).getHeader("token");
                if(token != null){
                    //token验证结果
                    int verify  = jwtUtils.verify(token);
                    if(verify != 1){
                        //验证失败
                        if(verify == 2){
                            map.put("code", 414);
                            map.put("msg","token已过期");
                            map.put("data",null);
                        }else if(verify == 0){
                            map.put("code", 414);
                            map.put("msg","用户信息验证失败");
                            map.put("data",null);
                        }
                    }else {
                        //验证成功，放行
                        filterChain.doFilter(servletRequest,servletResponse);
                        return;
                    }
                }else{
                    //token为空的返回
                    map.put("code", 414);
                    map.put("msg","未携带token信息");
                    map.put("data",null);
                }
            }
            JSONObject jsonObject = new JSONObject(map);
            servletResponse.setContentType("application/json");
            //设置响应的编码
            servletResponse.setCharacterEncoding("utf-8");
            //响应
            PrintWriter pw=servletResponse.getWriter();
            pw.write(jsonObject.toString());
            pw.flush();
            pw.close();
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
