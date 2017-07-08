package com.mark.domain;

/**
 * Created by gaowenfeng on 2017/6/2.
 * 类名为数据库名去掉tb_,并且首字母大写;
 * 属性名同数据库字段相同;
 * 主键名应为id；
 * 类型int对应Integer，varchar对应String（暂时只支持这两种类型，后续会添加）
 */

public class User {
    private Integer id;
    private String username;
    private String password;
    private Integer roleid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }
}
