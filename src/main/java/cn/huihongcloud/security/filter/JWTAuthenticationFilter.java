
package cn.huihongcloud.security.filter;

import cn.huihongcloud.component.JWTComponent;
import cn.huihongcloud.util.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 钟晖宏 on 2018/10/1
 */

public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

    private JWTComponent jwtComponent;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint) {
        super(authenticationManager, authenticationEntryPoint);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        jwtComponent = SpringContextUtil.getContext().getBean(JWTComponent.class);
//        super.doFilterInternal(request, response, chain);
//


        String token = request.getHeader("token");

        if (request.getRequestURI().contains("swagger-ui.html")) {
            SecurityContextHolder.getContext().setAuthentication(getAuthentication("swagger"));
            chain.doFilter(request, response);
        }



        if (token != null) {
//
            String token2 = token;
            String username = jwtComponent.verify(token);
//        String username = "root";

//        filterChain.doFilter(servletRequest, servletResponse);
            if (username == null) {
                response.setStatus(401);
            } else {

                request.setAttribute("username", username);
                SecurityContextHolder.getContext().setAuthentication(getAuthentication(username));
                chain.doFilter(request, response);
            }
        } else {
            chain.doFilter(request, response);
        }




    }

    private UsernamePasswordAuthenticationToken getAuthentication(String username) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        User principal = new User(username, "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, null, authorities);


    }
}

