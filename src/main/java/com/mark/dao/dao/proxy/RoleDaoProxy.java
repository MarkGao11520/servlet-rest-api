package com.mark.dao.dao.proxy;


import com.mark.dao.dao.IRoleDao;
import com.mark.dao.dao.impl.RoleDaoImpl;
import com.mark.domain.Role;

/**
 * Created by gaowenfeng on 2017/7/8.
 */
public class RoleDaoProxy extends DAOProxy<Role> implements IRoleDao {
    IRoleDao iRoleDao;
    public RoleDaoProxy(Role role) throws Exception {
        super(role);
        RoleDaoImpl roleDaoImpl = new RoleDaoImpl(super.dbc.getConnection(),role);
        this.iRoleDao = roleDaoImpl;
    }
}
