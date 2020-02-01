package com.orange.service;

public interface OmsPortalOrderService {
    /**
     * 下单
     *
     * @param orderId
     * @return
     */
    int insertOrder(long orderId);

    /**
     * 取消单个超时订单
     *
     * @param orderId
     */
    void cancelOrder(long orderId);
}
