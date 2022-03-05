package com.cas.service;

import com.cas.bean.CounterUpDO;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2022/3/5 4:04 下午
 * @desc
 */
public interface CounterUpService {

    int insert(CounterUpDO counterUpDO);

    int update(CounterUpDO counterUpDO);

    String queryById(String id);

}
