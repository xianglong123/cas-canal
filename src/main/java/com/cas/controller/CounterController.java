package com.cas.controller;

import com.cas.bean.CounterUpDO;
import com.cas.service.CounterUpService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2022/3/5 4:03 下午
 * @desc
 */
@RequestMapping("/canal")
@RestController
public class CounterController {

    @Resource
    private CounterUpService counterUpService;

    @PostMapping("add")
    public void add(@RequestBody CounterUpDO counterUpDO) {
        counterUpService.insert(counterUpDO);
    }

    @PostMapping("update")
    public void update(@RequestBody CounterUpDO counterUpDO) {
        counterUpService.update(counterUpDO);
    }

    @GetMapping("query")
    public String getCounter(@RequestParam("id") String id) {
        return counterUpService.queryById(id);
    }

}
