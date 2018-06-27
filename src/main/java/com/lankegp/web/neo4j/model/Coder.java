package com.lankegp.web.neo4j.model;

import com.lankegp.common.base.Neo4jBaseEntity;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

/**
 * Created by liugongrui on 2018/4/16.
 * @JsonProperty（"别名"） ：Json注解---> 给关系起别名
 */
@NodeEntity
public class Coder extends Neo4jBaseEntity {

    private String sex;

    private String hobby;

    @Relationship(type = "Like")
    private List<Player> players;

    @Relationship(type = "Have")
    private List<Cat> cats;

    public Coder() {

    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Cat> getCats() {
        return cats;
    }

    public void setCats(List<Cat> cats) {
        this.cats = cats;
    }
}
