package com.mark.dao.dao.proxy;



import com.mark.dao.dao.IDao;
import com.mark.dao.dao.impl.DaoImpl;
import com.mark.dao.dbc.DatabaseConnection;
import com.mark.dao.dbc.DatabaseConnectionFactory;

import java.util.List;

/**
 * Created by gaowenfeng on 2017/6/2.
 */
public abstract class DAOProxy<T> implements IDao<T> {
    protected DatabaseConnection dbc = null;
    private IDao<T> dao = null;
    public DAOProxy(T t)throws Exception{
        this.dbc = DatabaseConnectionFactory.getDatabaseConnection();
        DaoImpl<T> daoImpl = new DaoImpl(this.dbc.getConnection(),t);
        this.dao = daoImpl;
    }

    public boolean doCreate(T t) throws Exception {
        boolean flag = false;
        try {
            flag = this.dao.doCreate(t);
        }finally {
            this.dbc.close();
        }
        return flag;
    }

    public List<T> findAll() throws Exception {
        List<T> all = null;
        try {
            all = this.dao.findAll();
        }finally {
            this.dbc.close();
        }
        return all;
    }
}
