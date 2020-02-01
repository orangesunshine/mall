package com.orange.config;

import com.orange.dto.QueueEnum;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消息队列
 */
@Configuration
public class RabitMqConfig {
    /**
     * 订单取消队列绑定的交换机
     *
     * @return
     */
    @Bean
    DirectExchange orderExchange() {
        return ExchangeBuilder.directExchange(QueueEnum.QUEUE_ORDER_CANCEL.getExchange())
                .durable(true)
                .build();
    }

    /**
     * 订单延迟队列绑定的交换机
     *
     * @return
     */
    @Bean
    DirectExchange orderTtlExchange() {
        return ExchangeBuilder.directExchange(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getExchange())
                .durable(true)
                .build();
    }

    /**
     * 取消订单队列
     *
     * @return
     */
    @Bean
    Queue orderQueue() {
        return new Queue(QueueEnum.QUEUE_ORDER_CANCEL.getQueue());
    }

    /**
     * 订单取消延迟队列
     *
     * @return
     */
    @Bean
    Queue orderTtlQueue() {
        return QueueBuilder.durable(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getQueue())
                .withArgument("x-dead-letter-exchange", QueueEnum.QUEUE_ORDER_CANCEL.getExchange())//到期后转发的交换机
                .withArgument("x-dead-letter-routing-key", QueueEnum.QUEUE_ORDER_CANCEL.getRouteKey())//到期后转发的路由键
                .build();
    }

    /**
     * 订单队列绑定到交换机
     *
     * @param orderExchange
     * @param orderQueue
     * @return
     */
    @Bean
    Binding orderBinding(DirectExchange orderExchange, Queue orderQueue) {
        return BindingBuilder.bind(orderQueue).to(orderExchange).with(QueueEnum.QUEUE_ORDER_CANCEL.getRouteKey());
    }

    @Bean
    Binding orderTtlBinding(DirectExchange orderTtlExchange, Queue orderTtlQueue) {
        return BindingBuilder.bind(orderTtlQueue).to(orderTtlExchange).with(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getRouteKey());
    }
}
