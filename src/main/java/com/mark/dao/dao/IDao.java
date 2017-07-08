package com.mark.dao.dao;

import java.util.List;

/**
 * Created by gaowenfeng on 2017/6/2.
 */
public interface IDao<T> {
    boolean doCreate(T model)throws Exception;

    List<T> findAll() throws Exception;
}
