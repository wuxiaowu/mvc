package com.frame.learn.mvc.action;

import com.frame.learn.mvc.form.ActionForm;
import com.frame.learn.mvc.form.UserForm;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 15-5-21
 * Time: 下午10:03
 * To change this template use File | Settings | File Templates.
 */
public class UserAction  implements   Action{

    @Override
    public String execute(ActionForm actionForm) {
        if(null==actionForm){
            return "fail";
        }
        UserForm userForm=(UserForm)actionForm;
        if("admin".equals(userForm.getUsername())&&"123456".equals(userForm.getPassword())){
          return "success";
        }else{
          return "fail";
        }
    }
}
