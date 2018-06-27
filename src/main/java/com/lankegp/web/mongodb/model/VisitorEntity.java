package com.lankegp.web.mongodb.model;

import com.lankegp.common.annotation.SearchField;
import com.lankegp.common.base.MongoBaseEntity;
import com.lankegp.common.enums.SearchType;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * 访客信息
 * Created by liugongrui on 2017/12/22.
 */

@Document(collection = "visitor")
public class VisitorEntity extends MongoBaseEntity {

    @Id
    private String id;

    @SearchField(type = SearchType.LIKE)
    @NotEmpty(message = "姓名不能为空")
    private String name;

    @SearchField(type = SearchType.LIKE)
    @NotEmpty(message = "身份证不能为空")
    private String idnum;

    private String sex;//性别

    private String nation;//民族

    private String birthday;//出生日期

    private String issuing;//签发机关

    private String from;//有效期起

    private String to;//有效期止


    @SearchField(type = SearchType.LIKE)
    private String address;

    private String idcardId;//身份证照片

    private String picId;//现场照片

    @NotEmpty(message = "拜访户主不能为空")
    private String toRoom;

    private String toRoomId;

    private String visitingTime;//到访时间

    private String status;//预约状态（1：已申请，2：已同意）


    /**
     * 区域编码
     */
    private String areaCode;

    /**
     * 区域名称
     */
    private String areaName;

    /**
     * 操作 :0新增 1修改 2删除 3查询
     */
    private String operateStatus;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 接口所需字段
     * 身份证照片路径（以身份证号为照片名称） ,
     */
    private String photo;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdnum() {
        return idnum;
    }

    public void setIdnum(String idnum) {
        this.idnum = idnum;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getIssuing() {
        return issuing;
    }

    public void setIssuing(String issuing) {
        this.issuing = issuing;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdcardId() {
        return idcardId;
    }

    public void setIdcardId(String idcardId) {
        this.idcardId = idcardId;
    }

    public String getPicId() {
        return picId;
    }

    public void setPicId(String picId) {
        this.picId = picId;
    }

    public String getToRoom() {
        return toRoom;
    }

    public void setToRoom(String toRoom) {
        this.toRoom = toRoom;
    }

    public String getToRoomId() {
        return toRoomId;
    }

    public void setToRoomId(String toRoomId) {
        this.toRoomId = toRoomId;
    }

    public String getVisitingTime() {
        return visitingTime;
    }

    public void setVisitingTime(String visitingTime) {
        this.visitingTime = visitingTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getOperateStatus() {
        return operateStatus;
    }

    public void setOperateStatus(String operateStatus) {
        this.operateStatus = operateStatus;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}