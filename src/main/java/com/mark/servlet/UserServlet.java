package com.mark.servlet;

import com.alibaba.fastjson.JSON;
import com.mark.core.ResultGenerator;
import com.mark.core.ServiceException;
import com.mark.dao.factory.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by gaowenfeng on 2017/7/8.
 */
@WebServlet("/userServlet/*")
public class UserServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        try {
            String result = new String(JSON.toJSONString(ResultGenerator.genSuccessResult(DAOFactory.getIUserDAOInstance().findAll())).getBytes(),"utf-8");
            out.print(result);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("获取用户列表失败");
        }finally {
            out.close();
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
