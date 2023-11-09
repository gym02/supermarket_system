package com.example.supermarket.consumer;

import com.example.supermarket.entity.dto.VipLevel;
import com.example.supermarket.mapper.VipMapper;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

/**
 * @author 高垚淼
 * @version 1.0
 */
@Component
@Slf4j
public class PointLevelConvertConsumer {
    @Autowired
    VipMapper vipMapper;

    @RabbitListener(queues = "pointLevelConvert")
    @Transactional
    public void dealPointLevelConvert(VipLevel vipLevel, Message message, Channel channel) throws IOException {
        try {
            //先修改会员等级信息
            vipMapper.updateVipLevel(vipLevel);
            //根据会员等级修改所有会员的等级
            if(vipLevel.getDjMax() == null){
                vipMapper.updateAllVipLevelIfNull(vipLevel);
            }else{
                vipMapper.updateAllVipLevel(vipLevel);
            }
        } catch (Exception e) {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        }
    }
}
