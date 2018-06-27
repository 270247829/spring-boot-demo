package com.lankegp.common.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.neo4j.ogm.annotation.GraphId;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;

/**
 * Created by liugongrui on 2017/12/23.
 *
 *  @Transient 表示不参与序列化 不存到mongodb中
 *  @JsonInclude 表示返回json对象时，是否包含这个字段，NON_DEFAULT是条件
 */
public class Neo4jBaseEntity implements Serializable{

    /**
     * Neo4j会分配的ID（节点唯一标识 当前类中有效）
     */
    @GraphId
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Transient
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int start;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int length;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
