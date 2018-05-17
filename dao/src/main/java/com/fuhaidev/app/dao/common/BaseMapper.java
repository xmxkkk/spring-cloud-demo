package com.fuhaidev.app.dao.common;

/**
 * Created by xumingxun on 2018/4/28.
 */
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {

}