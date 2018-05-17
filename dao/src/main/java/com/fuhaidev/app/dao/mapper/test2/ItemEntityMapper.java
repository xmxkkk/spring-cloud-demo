package com.fuhaidev.app.dao.mapper.test2;

import com.fuhaidev.app.dao.common.BaseMapper;
import com.fuhaidev.app.dao.entity.test2.ItemEntity;

import javax.annotation.Resource;

@Resource(name = "test2MapperScannerConfigurer")
public interface ItemEntityMapper extends BaseMapper<ItemEntity> {
}