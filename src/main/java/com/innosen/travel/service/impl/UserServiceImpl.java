package com.innosen.travel.service.impl;


import com.innosen.travel.dao.UserDao;
import com.innosen.travel.dao.impl.UserDaoImpl;
import com.innosen.travel.entity.User;
import com.innosen.travel.service.UserService;
import com.innosen.travel.utils.MailUtil;
import com.innosen.travel.utils.UuidUtil;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public boolean register(User user) {
        user.setStatus("N");
        user.setCode(UuidUtil.getUuid());
        boolean flag = userDao.register(user);

        if (!flag) {
            return false;
        }

        String content = "点击<a href='http:localhost:8888/travel/activeUser?code=' " + user.getCode() + ">激活</a>用户，易良盛科技";
        MailUtil.sendMail(user.getEmail(),content,"用户激活邮件");
        return true;
    }

    @Override
    public boolean active(String code) {
        User user = userDao.active(code);
        if (user == null) {
            return false;
        }
        int line = userDao.changeStatus(user.getCode());
        if (line == 0) {
            return false;
        }
        return true;
    }

    @Override
    public User login(User user) {
        return userDao.loggin(user);
    }
}
