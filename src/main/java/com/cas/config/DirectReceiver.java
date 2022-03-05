package com.cas.config;

import com.alibaba.fastjson.JSONObject;
import com.cas.bean.CanalMessage;
import com.cas.common.CanalTypeEnum;
import com.cas.common.Constants;
import com.cas.utils.RedisFixUtil;
import com.cas.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 消费者
 */
@Component
public class DirectReceiver {

    private static Logger log = LoggerFactory.getLogger(DirectReceiver.class);

    @Resource
    private RedisUtil redisUtil;

    @RabbitHandler
    @RabbitListener(bindings = {@QueueBinding(value = @Queue(value = "canal_queue"), exchange = @Exchange(value = "canal_exchange"), key = "canal_exchange_routing")})
    public void process(String testMessage) {
        CanalMessage canalMessage = JSONObject.parseObject(testMessage, CanalMessage.class);
        String type = canalMessage.getType();
        switch (Objects.requireNonNull(CanalTypeEnum.getByCode(type))) {
            case DELETE:
                delete(canalMessage);
                break;
            case INSERT:
            case UPDATE:
                updateAndInsert(canalMessage);
                break;
            default:
                log.error("未知消息：[{}]", testMessage);
        }
        log.info("DirectReceiver消费者收到消息  : " + testMessage);
    }

    private void updateAndInsert(CanalMessage canalMessage) {
        List<Map<String, String>> maps = canalMessage.getData();
        for (Map<String, String> res : maps) {
            log.info("canal监听, 类型[{}], 更新后数据[{}]", canalMessage.getType(), res.get("counter"));
            redisUtil.setEx(RedisFixUtil.getFix(res.get("id")), res.get("counter"), Constants.TIME_OUT);
        }
    }

    private void delete(CanalMessage canalMessage) {
        List<Map<String, String>> maps = canalMessage.getData();
        for (Map<String, String> res : maps) {
            log.info("canal监听, 类型[{}], 更新后数据[{}]", canalMessage.getType(), res.get("counter"));
            redisUtil.del(RedisFixUtil.getFix(res.get("id")));
        }
    }


}
