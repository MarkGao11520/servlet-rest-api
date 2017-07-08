package com.mark.dao.dao.impl;


import com.mark.dao.dao.IUserDao;
import com.mark.domain.User;

import java.sql.Connection;

/**
 * Created by gaowenfeng on 2017/7/8.
 */
public class UserDaoImpl extends DaoImpl<User> implements IUserDao {

    public UserDaoImpl(Connection connection, User user) {
        super(connection, user);
    }
}
