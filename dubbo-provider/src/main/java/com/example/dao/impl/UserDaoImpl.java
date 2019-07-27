package com.example.dao.impl;

import com.example.dao.UserDao;
import org.springframework.stereotype.Service;

@Service
public class UserDaoImpl implements UserDao {

    @Override
    public String getDetail(String id) {
        return "用户余额：200元";
    }
}
