package com.lankegp.common.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


/**
 * 场所消息消费者
 * Created by liugongrui on 2018/2/13.
 */
@Component
public class PlaceConsumer {

    @KafkaListener(topics = {"place"})
    public void processMessage(String content) {
        System.out.println("====================接收到place=================");

    }
}
