package com.orange.service.impl;

import com.orange.component.CancelOrderSender;
import com.orange.dto.OrderParam;
import com.orange.service.OmsPortalOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OmsPortalOrderServiceImpl implements OmsPortalOrderService {
    private static Logger LOGGER = LoggerFactory.getLogger(OmsPortalOrderServiceImpl.class);
    @Autowired
    CancelOrderSender cancelOrderSender;

    @Override
    public int insertOrder(long orderId) {
        LOGGER.info("process insert order");
        sendDelayMessage(orderId);
        return 1;
    }

    @Override
    public void cancelOrder(long orderId) {
        LOGGER.info("cancelOrder  process");
    }

    public void sendDelayMessage(long orderId) {
        long delayTimes = 10 * 1000;
        cancelOrderSender.sendMessage(orderId, delayTimes);
    }
}
