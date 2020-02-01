package com.orange.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OrderTimeOutCancelTask {
    private Logger LOGGER = LoggerFactory.getLogger(OrderTimeOutCancelTask.class.getSimpleName());


//    @Scheduled(cron = "0/10 * * ? * ?")
//    private void cancelTimeOutOrder() {
//        LOGGER.info("取消订单 测试");
//    }
}
