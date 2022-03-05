package com.cas.service.Impl;

import com.alibaba.druid.util.StringUtils;
import com.cas.bean.CounterUpDO;
import com.cas.common.Constants;
import com.cas.dao.CounterUpMapper;
import com.cas.service.CounterUpService;
import com.cas.utils.RedisFixUtil;
import com.cas.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2022/3/5 4:05 下午
 * @desc
 */
@Service
public class CounterUpServiceImpl implements CounterUpService {

    private static final Logger log = LoggerFactory.getLogger(CounterUpServiceImpl.class);

    @Resource
    private CounterUpMapper counterUpMapper;

    @Resource
    private RedisUtil redisUtil;


    @Override
    public int insert(CounterUpDO counterUpDO) {
        int res = counterUpMapper.insert(counterUpDO);
        if (res != 1) {
            log.error("新增数据失败");
        } else {
            log.info("新增成功");
        }
        return res;
    }

    @Override
    public int update(CounterUpDO counterUpDO) {
        int res = counterUpMapper.update(counterUpDO);
        if (res != 1) {
            log.error("更新数据失败");
        } else {
            redisUtil.del(RedisFixUtil.getFix(counterUpDO.getId()));
            log.info("更新成功,缓存已删除，ID=【{}】", counterUpDO.getId());
        }
        return res;
    }

    @Override
    public String queryById(String id) {
        String counter = (String) redisUtil.get(RedisFixUtil.getFix(id));
        if (!StringUtils.isEmpty(counter)) {
            // 如果缓存中能查询到计数器，则将计数器返回
            log.info("id=[{}] 缓存值为【{}】", id, counter);
            return counter;
        } else {
            // 如果计数器是空的，则查询，再回填缓存
            CounterUpDO aDo = counterUpMapper.queryById(id);
            if (aDo == null) {
                return "null";
            }
            String co = String.valueOf(aDo.getCounter());
            redisUtil.setEx(RedisFixUtil.getFix(id), co, Constants.TIME_OUT);
            log.info("id = [{}]缓存为空，走DB回填缓存，回填值为=【{}】", id, co);
            return co;
        }
    }
}
