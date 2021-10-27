package com.gala.bug.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/bug")
//@WebServlet("/bug")
public class BugServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("BugServlet  doGet");
        resp.getWriter().println("hello you do visit BugServlet doGet");
//        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("BugServlet  doPost");
        resp.getWriter().println("hello you do visit BugServlet doPost");
//        super.doPost(req, resp);
    }

    //必须实现父类的service()方法
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        HttpServletRequest request;
        HttpServletResponse response;
        try{
            request=(HttpServletRequest)servletRequest;
            response=(HttpServletResponse)servletResponse;
        }catch(ClassCastException e){
            throw new ServletException("non-http request or response");
        }
        //调用service()方法
        System.out.println("BugServlet  service");
        response.getWriter().println("visit BugServlet service");

        // 调用doGet 或者 doPost
        super.service(request,response);
    }
}
