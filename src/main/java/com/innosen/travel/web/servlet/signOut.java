package com.innosen.travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.innosen.travel.entity.Message;
import com.innosen.travel.utils.ResUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/signOut")
public class signOut extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String user = (String) session.getAttribute("user");
        resp.setHeader("content-type", "application/json;charset=utf-8");
        if (user != null) {
            session.invalidate();
            Message msg = ResUtil.resMsg(1, "退出成功");
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(resp.getWriter(),msg);
        } else {
            Message msg = ResUtil.resMsg(0, "退出失败");
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(resp.getWriter(),msg);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
