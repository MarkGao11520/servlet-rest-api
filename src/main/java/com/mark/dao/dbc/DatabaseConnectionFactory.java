package com.mark.dao.dbc;

/**
 * Created by gaowenfeng on 2017/6/2.
 */
public class DatabaseConnectionFactory {
    public static DatabaseConnection getDatabaseConnection() throws Exception {
        return new MySQLDatabaseConnection();
    }
}
