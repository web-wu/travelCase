package com.innosen.travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.innosen.travel.entity.Message;
import com.innosen.travel.service.UserService;
import com.innosen.travel.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/activeUser")
public class ActiveUser extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String code = req.getParameter("code");
        UserService service = new UserServiceImpl();
        boolean flag = service.active(code);
        resp.setHeader("content-type","application/json");
        resp.setCharacterEncoding("utf-8");
        ObjectMapper mapper = new ObjectMapper();
        Message msg = new Message();
        if (flag) {
            msg.setStatus(1);
            msg.setMsg("用户已激活");
            mapper.writeValue(resp.getWriter(),msg);
        } else {
            msg.setStatus(0);
            msg.setMsg("激活发生错误，请联系管理员");
            mapper.writeValue(resp.getWriter(),msg);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
