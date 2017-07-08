package com.mark.dao.dao.impl;


import com.mark.dao.dao.IRoleDao;
import com.mark.domain.Role;

import java.sql.Connection;

/**
 * Created by gaowenfeng on 2017/7/8.
 */
public class RoleDaoImpl extends DaoImpl<Role> implements IRoleDao {
    public RoleDaoImpl(Connection connection, Role role) {
        super(connection, role);
    }
}
