package com.lankegp.common.base;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.lankegp.common.utils.JsonUtils;

import java.io.Serializable;

/**
 * ResultReturn通用结果返回类
 * Created by liugongrui on 2017/12/22.
 */
public class R implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 返回成功，默认fase失败
     */
    private boolean success;

    /**
     * 返回消息内容
     *  一般：失败原因；成功提示
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    /**
     * Object类
     *  一般：Map;List;Entity
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    /**
     * 分页start
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer start;

    /**
     * 分页limit
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer limit;

    /**
     * 当前页
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer page;

    /**
     * 总数
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private long total;


    @Override
    public String toString() {
        return JsonUtils.toString(this);
    }

    public boolean isSuccess() {
        return success;
    }

    public R setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public R setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public R setData(Object data) {
        this.data = data;
        return this;
    }

    public Integer getStart() {
        return start;
    }

    public R setStart(Integer start) {
        this.start = start;
        return this;
    }

    public Integer getLimit() {
        return limit;
    }

    public R setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public Integer getPage() {
        return page;
    }

    public R setPage(Integer page) {
        this.page = page;
        return this;
    }

    public long getTotal() {
        return total;
    }

    public R setTotal(long total) {
        this.total = total;
        return this;
    }
}
