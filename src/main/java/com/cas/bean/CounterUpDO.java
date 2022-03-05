package com.cas.bean;

import java.time.LocalDateTime;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2022/3/5 3:54 下午
 * @desc
 */
public class CounterUpDO {

    private String id;

    private int counter;

    private LocalDateTime updateTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
