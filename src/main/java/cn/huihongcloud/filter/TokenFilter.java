package cn.huihongcloud.filter;

import cn.huihongcloud.component.JWTComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 钟晖宏 on 2018/5/6
 */
//@WebFilter(urlPatterns = {"/auth_api/*"})
//@Component
public class TokenFilter implements Filter {

    @Autowired
    private JWTComponent jwtComponent;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = request.getHeader("token");

        String username = jwtComponent.verify(token);
//        filterChain.doFilter(servletRequest, servletResponse);
        if (username == null) {
            response.setStatus(401);
        }
        else {
            request.setAttribute("username", username);
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
