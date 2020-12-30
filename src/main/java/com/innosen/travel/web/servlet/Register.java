package com.innosen.travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.innosen.travel.entity.Message;
import com.innosen.travel.entity.User;
import com.innosen.travel.service.UserService;
import com.innosen.travel.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/register")
public class Register extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        String checkCode = req.getParameter("check");
        String verify_code = (String) req.getSession().getAttribute("checkCode");
        req.getSession().removeAttribute("checkCode");
        resp.setHeader("content-type","application/json;charset=utf-8");
        if (verify_code == null || !verify_code.equalsIgnoreCase(checkCode)) {
            Message msg = new Message();
            msg.setStatus(0);
            msg.setMsg("验证码错误！！！");
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(resp.getWriter(),msg);
            return;
        }

        Map<String, String[]> map = req.getParameterMap();
        User user = new User();

        try {
            BeanUtils.populate(user,map);
            UserService service = new UserServiceImpl();
            boolean flag = service.register(user);
            if (flag) {
                Message msg = new Message();
                msg.setStatus(1);
                msg.setMsg("注册成功！！！");
                ObjectMapper mapper = new ObjectMapper();
                mapper.writeValue(resp.getWriter(),msg);
            } else {
                Message msg = new Message();
                msg.setStatus(0);
                msg.setMsg("注册失败！！！");
                ObjectMapper mapper = new ObjectMapper();
                mapper.writeValue(resp.getWriter(),msg);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
