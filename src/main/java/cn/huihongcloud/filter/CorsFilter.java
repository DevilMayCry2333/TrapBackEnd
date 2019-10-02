package cn.huihongcloud.filter;

/**
 * Created by 钟晖宏 on 2018/4/27.
 */
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
//
//@Component
//@Order(Integer.MIN_VALUE)
public class CorsFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest reqs = (HttpServletRequest) req;
        response.setHeader("Access-Control-Allow-Origin",reqs.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,PATCH");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,token,Content-Type");
        response.setHeader("Access-Control-Max-Age", "3600");
        if (reqs.getMethod().toLowerCase().equals("options")) {
            response.setStatus(200);
        } else {
            chain.doFilter(req, res);
        }
    }
    public void init(FilterConfig filterConfig) {}
    public void destroy() {}
}