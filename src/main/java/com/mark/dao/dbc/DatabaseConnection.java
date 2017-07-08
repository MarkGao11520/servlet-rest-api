package com.mark.dao.dbc;

import java.sql.Connection;

/**
 * Created by gaowenfeng on 2017/6/2.
 */
public interface DatabaseConnection {
    Connection getConnection();
    void close() throws Exception;
}
