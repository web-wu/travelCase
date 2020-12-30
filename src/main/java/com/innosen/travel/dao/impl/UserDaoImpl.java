package com.innosen.travel.dao.impl;

import com.innosen.travel.dao.UserDao;
import com.innosen.travel.entity.User;
import com.innosen.travel.utils.JdbcUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JdbcUtil.getDataSource());
    @Override
    public boolean register(User user) {
        try {
            String sql = "insert into tab_user(username,password,name,birthday,sex,telephone,email,status,code) values(?,?,?,?,?,?,?,?,?)";
            int line = template.update(sql,
                    user.getUsername(),
                    user.getPassword(),
                    user.getName(),
                    user.getBirthday(),
                    user.getSex(),
                    user.getTelephone(),
                    user.getEmail(),
                    user.getStatus(),
                    user.getCode());
            if (line == 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User active(String code) {
        try {
            String sql = "delect * from tab_user where code = ?";
            return template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), code);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int changeStatus(String code) {
        try {
            String sql = "update Tab_user set status = ? where code = ?";
            return template.update(sql,"Y",code);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public User loggin(User user) {
        try {
            String sql = "select * from tab_user where username = ? and password = ?";
            return template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),user.getUsername(), user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
