package com.mark.dao.dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Created by gaowenfeng on 2017/6/2.
 */
public class MySQLDatabaseConnection implements DatabaseConnection{

    private Connection conn = null;
    public MySQLDatabaseConnection() throws Exception{
        try {
            String driverName = "com.mysql.jdbc.Driver";   //获取属性文件中的内容
            String url = "jdbc:mysql://localhost:3306/gwf?useUnicode=true&characterEncoding=utf-8";
            String uname = "root";
            String upwd = "zrkj123";
            Class.forName(driverName);
            this.conn = DriverManager.getConnection(url,uname,upwd);
        }catch (Exception e){
            throw e;
        }
    }

    public Connection getConnection() {
        return this.conn;
    }

    public void close() throws Exception{
        if(this.conn!=null){
            try {
                this.conn.close();
            }catch (Exception e){
                throw e;
            }
        }
    }
}
