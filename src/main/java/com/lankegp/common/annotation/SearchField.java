package com.lankegp.common.annotation;

import com.lankegp.common.enums.SearchType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

/**
 * 用于指定搜索字段的搜索类型 例如：模糊搜索，精准搜素，时间范围内搜索
 * Created by liugongrui on 2017/12/25.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = { FIELD })
public @interface SearchField {
    SearchType type() default SearchType.EQ;
}
