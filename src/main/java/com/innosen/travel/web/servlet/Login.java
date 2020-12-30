package com.innosen.travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.innosen.travel.entity.Message;
import com.innosen.travel.entity.User;
import com.innosen.travel.service.UserService;
import com.innosen.travel.service.impl.UserServiceImpl;
import com.innosen.travel.utils.ResUtil;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String check = req.getParameter("checkCode");
        HttpSession session = req.getSession();
        String checkCode = (String) session.getAttribute("checkCode");
        session.removeAttribute("checkCode");
        ObjectMapper mapper = new ObjectMapper();
        resp.setHeader("content-type","application/json;charset=utf-8");
        if (checkCode != null && check != null && checkCode.equalsIgnoreCase(check)) {
            Map<String, String[]> map = req.getParameterMap();
            User user = new User();
            try {
                BeanUtils.populate(user,map);
                UserService service = new UserServiceImpl();
                User _user = service.login(user);
                if (_user != null && "Y".equals(_user.getStatus())) {
                    session.setAttribute("user",_user);
                    Message msg = ResUtil.resMsg(1, "登录成功");
                    mapper.writeValue(resp.getWriter(),msg);
                } else {
                    Message msg = ResUtil.resMsg(0, "用户名或密码错误");
                    mapper.writeValue(resp.getWriter(),msg);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        } else {
            Message msg = ResUtil.resMsg(0, "验证码错误");
            mapper.writeValue(resp.getWriter(),msg);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
