package com.lankegp.web.neo4j.model;

import com.lankegp.common.base.Neo4jBaseEntity;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Created by liugongrui on 2018/4/16.
 */
@NodeEntity
public class Cat extends Neo4jBaseEntity {

    private String color;

    public Cat() {

    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}