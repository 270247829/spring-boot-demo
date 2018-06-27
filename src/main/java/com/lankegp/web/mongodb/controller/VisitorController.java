package com.lankegp.web.mongodb.controller;

import com.lankegp.common.base.BaseController;
import com.lankegp.common.base.R;
import com.lankegp.web.mongodb.model.VisitorEntity;
import com.lankegp.web.mongodb.service.VisitorService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by liugongrui on 2017/12/22.
 */
@RestController
@RequestMapping("/visitor")
public class VisitorController extends BaseController<VisitorService> {

    /**
     * 访客信息添加
     *
     * @return
     */
    @RequestMapping("/add")
    public R add(@Valid VisitorEntity entity, BindingResult result) {
        R r = valid(result);
        if (r != null) {
            return r;
        }
        r = baseService.add(entity);
        return r;
    }

    /**
     * 修改访客信息
     *
     * @return
     */
    @RequestMapping("/edit")
    public R edit(@Valid VisitorEntity entity, BindingResult result) {
        R r = valid(result);
        if (r != null) {
            return r;
        }
        r = baseService.edit(entity);
        return r;
    }

    /**
     * 删除访客信息
     *
     * @return
     */
    @RequestMapping("/delete")
    public R delete(String id) {
        R r = baseService.delete(id);
        return r;
    }

    /**
     * 访客信息列表
     *
     * @return
     */
    @RequestMapping("/list")
    public R list(VisitorEntity queryModel, HttpSession session) {
        R r = baseService.list(queryModel);
        return r;
    }

    /**
     * 访客信息详情
     *
     * @return
     */
    @RequestMapping("/detail")
    public R detail(String id) {
        R r = baseService.detail(id);
        return r;
    }


}
