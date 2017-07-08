package com.mark.dao.dao.proxy;


import com.mark.dao.dao.IUserDao;
import com.mark.dao.dao.impl.UserDaoImpl;
import com.mark.domain.User;

/**
 * Created by gaowenfeng on 2017/7/8.
 */
public class UserDaoProxy extends DAOProxy<User> implements IUserDao {
    private IUserDao iUserDao;
    public UserDaoProxy(User u) throws Exception {
        super(u);
        UserDaoImpl userDaoImpl = new UserDaoImpl(super.dbc.getConnection(),u);
        this.iUserDao = userDaoImpl;
    }
}
