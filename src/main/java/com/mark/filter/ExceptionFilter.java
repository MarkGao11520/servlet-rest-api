package com.mark.filter;

import com.alibaba.fastjson.JSON;
import com.mark.core.Result;
import com.mark.core.ResultCode;
import com.mark.core.ServiceException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by gaowenfeng on 2017/7/8.
 */
//@WebFilter(urlPatterns = "/*")
public class ExceptionFilter implements Filter {
//    private final Logger logger = LoggerFactory.getLogger(ExceptionFilter.class);

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Result result = new Result();
        try {
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            if(e instanceof ServiceException){//如果是你定义的业务异常
                System.out.println("1");
                request.setAttribute("ServiceException", e);//存储业务异常信息类
                result.setCode(ResultCode.FAIL).setMessage(e.getMessage());
//                logger.info(e.getMessage());
            }else {
                result.setCode(ResultCode.INTERNAL_SERVER_ERROR).setMessage("接口 [" + request.getRequestURI() + "] 内部错误，请联系管理员");
                String message = String.format("接口 [%s] 出现异常，异常摘要：%s",
                        request.getRequestURI(),
                        e.getMessage());
//                logger.error(message, e);
            }
            response.getWriter().append(JSON.toJSONString(result));
        }
    }

    public void destroy() {

    }
}
