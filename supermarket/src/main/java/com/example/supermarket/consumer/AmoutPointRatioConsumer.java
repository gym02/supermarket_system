package com.example.supermarket.consumer;

import com.example.supermarket.service.VipService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author 高垚淼
 * @version 1.0
 */
@Component
@Slf4j
public class AmoutPointRatioConsumer {

    @Autowired
    VipService vipService;

    @RabbitListener(queues = "amountPointRatio")
    public void dealAmountPointRatio(Double amountPointRatio, Message message, Channel channel) throws IOException {
        try {
            vipService.dealAmoutPointRatio(amountPointRatio);

        } catch (Exception e) {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,false);
        }
    }
}
