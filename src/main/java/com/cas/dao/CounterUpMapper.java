package com.cas.dao;

import com.cas.bean.CounterUpDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2022/3/5 3:56 下午
 * @desc
 */
@Mapper
public interface CounterUpMapper {

    int insert(CounterUpDO counterUpDO);

    int update(CounterUpDO counterUpDO);

    CounterUpDO queryById(String id);
}
