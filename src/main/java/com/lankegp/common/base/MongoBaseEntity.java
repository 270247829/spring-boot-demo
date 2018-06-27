package com.lankegp.common.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;

/**
 * Created by liugongrui on 2017/12/23.
 *
 *  @Transient 表示不参与序列化 不存到mongodb中
 *  @JsonInclude 表示返回json对象时，是否包含这个字段，NON_DEFAULT是条件
 */
public class MongoBaseEntity implements Serializable{

    @Transient
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int start;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int length;

    /**
     * 小区id
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String communityId;

    /**
     * 小区名称
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String communityName;

    /**
     * 创建人
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String creator;

    /**
     * 创建人
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String creatorId;

    /**
     * 创建时间
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String createTime;

    /**
     * 更新时间
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String updateTime;


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

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }
}
