package com.mark.dao.dao.impl;


import com.mark.dao.dao.IDao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaowenfeng on 2017/6/2.
 */
public class DaoImpl<T> implements IDao<T> {
    protected Connection connection = null;
    protected PreparedStatement pstmt = null;
    protected String tableName;
    protected Class tClass;
    protected Field[] fields;

    public DaoImpl(Connection connection, T t) {
        this.connection = connection;
        tableName = getTableName(t);
    }

    private String getTableName(T t) {
        String classname;
        tClass = t.getClass();
        fields = tClass.getDeclaredFields();
        classname = (classname = t.getClass().toString()).substring(classname.lastIndexOf('.') + 1);
        String tableName = "sys_" + classname.toLowerCase();
        return tableName;
    }


    public boolean doCreate(T model) throws Exception {

        boolean flag = true;
        StringBuilder sql = new StringBuilder();
        sql.append("insert into ").append(tableName).append("(");
        int j = 0;
        for (int i = 0; i < fields.length; i++) {
            Method method = tClass.getMethod(getGetMethod(fields[i].getName()));
            if (!fields[i].getName().equals("id")&&method.invoke(model)!=null) {
                if (j != 0)
                    sql.append(",").append(fields[i].getName());
                else
                    sql.append(fields[i].getName());
                j++;
            }
        }
        sql.append(") values(");
        for (int i = 0; i < j; i++) {
            if (i != j-1)
                sql.append("?,");
            else
                sql.append("?");
        }
        sql.append(")");
        this.pstmt = this.connection.prepareStatement(sql.toString());
        for (int i = 0, k = 1; i < fields.length; i++) {
            if (!fields[i].getName().equals("id")) {
                Object result;
                if (fields[i].getType() == Integer.class) {
                    Method method = tClass.getMethod(getGetMethod(fields[i].getName()));
                    if ((result = method.invoke(model)) != null)
                    this.pstmt.setInt(k, (Integer) result);
                } else if (fields[i].getType() == String.class) {
                    Method method = tClass.getMethod(getGetMethod(fields[i].getName()));
                    if ((result = method.invoke(model)) != null)
                    this.pstmt.setString(k, (String) result);
                }
                k++;
            }
        }
        if (this.pstmt.executeUpdate() > 0) {
            flag = true;
        }
        this.pstmt.close();
        return flag;
    }

    public List<T> findAll() throws Exception {
        List<T> all = new ArrayList<T>();
        String sql = "select * from " + tableName;
        Object o = null;
        this.pstmt = this.connection.prepareStatement(sql);
        ResultSet rs = this.pstmt.executeQuery();

        while (rs.next()) {
            o = tClass.newInstance();
            for (int i = 0, j = 1; i < fields.length; i++, j++) {
                StringBuilder methodName = new StringBuilder();
                if (fields[i].getType() == Integer.class) {
                    Method method = tClass.getMethod(getSetMethod(fields[i].getName()), Integer.class);
                    method.invoke(o, rs.getInt(j));
                } else if (fields[i].getType() == String.class) {
                    Method method = tClass.getMethod(getSetMethod(fields[i].getName()), String.class);
                    method.invoke(o, rs.getString(j));
                }
            }
            all.add((T) o);
        }
        this.pstmt.close();
        return all;
    }

    private static String getSetMethod(String fileName) {
        return "set" + fileName.substring(0, 1).toUpperCase() + fileName.substring(1);
    }

    private static String getGetMethod(String fileName) {
        return "get" + fileName.substring(0, 1).toUpperCase() + fileName.substring(1);
    }
}
