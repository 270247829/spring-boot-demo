package com.lankegp.web.neo4j.model;

import com.lankegp.common.base.Neo4jBaseEntity;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Created by liugongrui on 2018/4/16.
 */
@NodeEntity
public class Player extends Neo4jBaseEntity {

    private double height;
    private String location;

    public Player(){

    }

    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
}