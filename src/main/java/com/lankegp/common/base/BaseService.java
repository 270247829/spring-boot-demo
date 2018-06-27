package com.lankegp.common.base;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 服务层基类
 * Created by liugongrui on 2017/12/22.
 */
public class BaseService<T> {

    @Autowired
    protected T baseDao;
}
