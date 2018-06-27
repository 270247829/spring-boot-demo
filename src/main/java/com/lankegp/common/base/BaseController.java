package com.lankegp.common.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * 控制器基类
 * Created by liugongrui on 2017/12/22.
 *
 */
public class BaseController<T> {

    @Autowired
    protected T baseService;

    public R valid(BindingResult result){
        R r = new R();
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                r.setMessage(error.getDefaultMessage());
                return r.setSuccess(false);
            }
        }
        return null;
    }
}
