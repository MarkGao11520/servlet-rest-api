package com.mark.dao.factory;


import com.mark.dao.dao.IDao;
import com.mark.dao.dao.IRoleDao;
import com.mark.dao.dao.proxy.RoleDaoProxy;
import com.mark.dao.dao.proxy.UserDaoProxy;
import com.mark.domain.Role;
import com.mark.domain.User;

/**
 * Created by gaowenfeng on 2017/6/2.
 */
public class DAOFactory {
    public static IDao getIUserDAOInstance()throws Exception{
        return new UserDaoProxy(new User());
    }

    public static IRoleDao getIRoleDaoInstance()throws Exception{
        return new RoleDaoProxy(new Role());
    }
}
