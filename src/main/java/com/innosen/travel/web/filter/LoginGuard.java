package com.innosen.travel.web.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.innosen.travel.entity.Message;
import com.innosen.travel.entity.User;
import com.innosen.travel.utils.ResUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/travel/*")
public class LoginGuard implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String uri = req.getRequestURI();
        if (uri.contains("/login.html") || uri.contains("/login") || uri.contains("/css/*") || uri.contains("/js/*") || uri.contains("/fonts/*") || uri.contains("/img/*") || uri.contains("/images/*")) {
            filterChain.doFilter(servletRequest,servletResponse);
        } else {
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");
            if (user != null) {
                filterChain.doFilter(servletRequest,servletResponse);
            } else {
               /* resp.setHeader("content-type", "application/json;charset=utf-8");
                Message msg = ResUtil.resMsg(0, "您还未登陆，请先登录再访问");
                ObjectMapper mapper = new ObjectMapper();
                mapper.writeValue(resp.getWriter(), msg);*/
                req.getRequestDispatcher("login.html").forward(req,resp);
            }
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void destroy() {

    }
}
