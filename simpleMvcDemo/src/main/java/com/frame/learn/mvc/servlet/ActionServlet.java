package com.frame.learn.mvc.servlet;

import com.frame.learn.mvc.action.Action;
import com.frame.learn.mvc.form.ActionForm;
import com.frame.learn.mvc.mapping.ActionMapping;
import com.frame.learn.mvc.utils.FullBean;
import com.frame.learn.mvc.utils.XmlBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class ActionServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("GBK");
        response.setCharacterEncoding("GBK");
      String ServletPath=  request.getServletPath();
      String path= this.getPath(ServletPath);

        Map<String,XmlBean> map =(Map<String,XmlBean>) this.getServletContext().getAttribute("struts");
        XmlBean xmlBean=map.get(path);
        String formClass=xmlBean.getFormClass();
        ActionForm  actionForm=FullBean.fullBean(request,formClass);
        String actionType=xmlBean.getActionType();

        Action action=null;
        String url="";
        try {
            Class clazz=Class.forName(actionType);
            action=(Action)clazz.newInstance();
            String urlKey= action.execute(actionForm);
            url= xmlBean.getActionForward().get(urlKey);
        } catch (Exception e) {
            e.printStackTrace();
        }

        RequestDispatcher dispatcher=request.getRequestDispatcher(url);
        dispatcher.forward(request,response);

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    public String getPath(String path){
        return path.split("\\.")[0];
    }
}
