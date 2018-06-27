package com.lankegp.web.mongodb.service;

import com.lankegp.common.base.BaseService;
import com.lankegp.common.base.Convention;
import com.lankegp.common.base.R;
import com.lankegp.web.mongodb.dao.VisitorDao;
import com.lankegp.web.mongodb.model.VisitorEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liugongrui on 2017/12/22.
 */
@Service
public class VisitorService extends BaseService<VisitorDao> {


    public R add(VisitorEntity entity) {
        R r = new R();
        try {
            baseDao.save(entity);
        } catch (Exception e) {
            return r.setSuccess(false).setMessage(Convention.SYSTEM_ERROR_MESSAGE);
        }
        return r.setSuccess(true).setMessage(Convention.ADD_SUCCESS_MESSAGE);
    }

    public R list(VisitorEntity queryModel) {
        R r = new R();
        long count;
        List<VisitorEntity> list = new ArrayList<>();
        try {
            list = baseDao.findPage(queryModel, queryModel.getStart(), queryModel.getLength());
            count = baseDao.count(queryModel);
        } catch (Exception e) {
            return r.setSuccess(false).setMessage(Convention.SYSTEM_ERROR_MESSAGE);
        }
        return r.setSuccess(true).setData(list).setTotal(count);
    }

    public R detail(String id) {
        R r = new R();
        VisitorEntity visitorEntity;
        try {
            visitorEntity = baseDao.findById(id);
        } catch (Exception e) {
            return r.setSuccess(false).setMessage(Convention.SYSTEM_ERROR_MESSAGE);
        }
        return r.setSuccess(true).setData(visitorEntity);
    }

    public R edit(VisitorEntity entity) {
        R r = new R();
        try {
            baseDao.updateSelective(entity);
        } catch (Exception e) {
            return r.setSuccess(false).setMessage(Convention.SYSTEM_ERROR_MESSAGE);
        }
        return r.setSuccess(true).setMessage(Convention.EDIT_SUCCESS_MESSAGE);
    }

    public R delete(String id) {
        R r = new R();
        try {
            baseDao.deleteById(id);
        } catch (Exception e) {
            return r.setSuccess(false).setMessage(Convention.SYSTEM_ERROR_MESSAGE);
        }
        return r.setSuccess(true).setMessage(Convention.DELETE_SUCCESS_MESSAGE);
    }

}
