package com.frame.learn.mvc.action;

import com.frame.learn.mvc.form.ActionForm;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 15-5-16
 * Time: 下午7:00
 * To change this template use File | Settings | File Templates.
 */
public interface Action {
    public  String execute(ActionForm actionForm);
}
