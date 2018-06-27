package com.lankegp.web.neo4j.controller;

import com.lankegp.web.neo4j.dao.CoderDao;
import com.lankegp.web.neo4j.model.Coder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * Created by liugongrui on 2018/4/16.
 */
@RestController
@RequestMapping("/coder") //restful风格的api接口
public class CoderController {


    @Autowired
    CoderDao coderDao;

    @RequestMapping("/get")
    public Coder coder(@RequestParam(value="name") String name){
        return coderDao.findByName(name);
    }

    @RequestMapping("/add")
    public void save(@RequestParam(value="name") String name){
        Coder coder = new Coder();
        coder.setName(name);
        coderDao.save(coder);
    }

    @PostMapping("/save")
    @Transactional
    public void Create(@RequestBody Coder coder) throws Exception{
        Coder result = coderDao.save(coder);
    }
}