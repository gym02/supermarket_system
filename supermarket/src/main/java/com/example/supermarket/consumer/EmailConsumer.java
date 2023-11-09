package com.example.supermarket.consumer;

import cn.hutool.extra.mail.MailUtil;
import com.example.supermarket.entity.Festival;
import com.example.supermarket.entity.dto.VipDTO;
import com.example.supermarket.entity.dto.VipFestivalDTO;
import com.example.supermarket.mapper.VipFestivalMapper;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author 高垚淼
 * @version 1.0
 */
@Component
@Slf4j
public class EmailConsumer {

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    VipFestivalMapper vipFestivalMapper;

    @RabbitListener(queues = "email")
    public void dealEmail(String email, Message message, Channel channel) throws IOException {
        try {
            // 生成验证码
            String code = UUID.randomUUID().toString().substring(0, 4);
            // 存入token下次比较
            redisTemplate.opsForValue().set("code", code, 10, TimeUnit.MINUTES);
            // 发送邮件
            MailUtil.send(email, "商超管理系统——找回会员密码", "如果不是本人，请忽略此邮件。验证码为：" + code + "，请妥善保管！", false);
            //失败，不重回队列。使用其他逻辑处理
        } catch (Exception e) {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        }

    }

    //    生日庆祝邮件发送，消费者1
    @RabbitListener(queues = "emaillist")
    public void dealEmaillist(List<VipDTO> emaillist, Message message, Channel channel) throws IOException {
        try {
            for (VipDTO vip : emaillist) {
                String birthdayBless = "尊敬的vip" + vip.getHyLevel() + "用户，您好！" + "商超系统在这里祝您生日快乐！";
                // 发送邮件
                MailUtil.send(vip.getGkEmail(), "商超系统祝您生日快乐！", birthdayBless, false);
            }
        } catch (Exception e) {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        }
    }

    //    生日庆祝邮件发送，消费者2
    @RabbitListener(queues = "emaillist")
    public void dealEmaillist2(List<VipDTO> emaillist, Message message, Channel channel) throws IOException {
        try {
            for (VipDTO vip : emaillist) {
                String birthdayBless = "尊敬的" + vip.getHyLevel() + "用户，您好！" + "商超系统在这里祝您生日快乐！";
                // 发送邮件
                MailUtil.send(vip.getGkEmail(), "商超系统祝您生日快乐！", birthdayBless, false);
            }

        } catch (Exception e) {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        }
    }

    //    节日快乐邮件发送，消费者1
    @RabbitListener(queues = "emaillist2")
    public void dealEmaillist3(Festival festival, Message message, Channel channel) throws IOException {
        try {
            //编辑邮件发送内容
            String birthdayBless = "尊敬的用户，您好！" + festival.getJrName() + "快乐！" + festival.getJrBless();
            //查询所有会员信息的邮箱
            List<VipDTO> vips = vipFestivalMapper.getVips();
            for (VipDTO v : vips) {
                // 发送邮件
                MailUtil.send(v.getGkEmail(), "商超系统祝您节日快乐！", birthdayBless, false);
            }
        } catch (Exception e) {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        }
    }

    //    节日快乐邮件发送，消费者2
    @RabbitListener(queues = "emaillist2")
    public void dealEmaillist4(Festival festival, Message message, Channel channel) throws IOException {
        try {
            //编辑邮件发送内容
            String birthdayBless = "尊敬的用户，您好！" + festival.getJrName() + "快乐！" + festival.getJrBless();
            //查询所有会员信息的邮箱
            List<VipDTO> vips = vipFestivalMapper.getVips();
            for (VipDTO v : vips) {
                // 发送邮件
                MailUtil.send(v.getGkEmail(), "商超系统祝您节日快乐！", birthdayBless, false);
            }
        } catch (Exception e) {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        }
    }
}
