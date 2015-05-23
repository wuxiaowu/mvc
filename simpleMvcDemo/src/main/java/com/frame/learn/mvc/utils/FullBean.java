package com.frame.learn.mvc.utils;



import com.frame.learn.mvc.form.ActionForm;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;

public class FullBean {

    public static ActionForm fullBean(HttpServletRequest request, String className){
        ActionForm o=null;
        try {
          Class clazz=  Class.forName(className);
          o= (ActionForm)clazz.newInstance();
          Field[] fields=clazz.getDeclaredFields();
          for(Field f: fields){
              f.setAccessible(true);
              f.set(o,request.getParameter(f.getName()));
              f.setAccessible(false);
          }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }
}
