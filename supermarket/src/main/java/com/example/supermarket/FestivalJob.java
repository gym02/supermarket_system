package com.example.supermarket;

import com.example.supermarket.entity.Festival;
import com.example.supermarket.service.VipFestivalService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 高垚淼
 * @version 1.0
 */
@Component
@EnableScheduling
@EnableAsync
public class FestivalJob {

    // 定义一个日期格式化的对象
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    VipFestivalService vipFestivalService;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Scheduled(cron = "0 49 14 * * * ")
    public void excute() {
        // 获取当前日期
        Date today = new Date();
        // 格式化当前日期
        String todayStr = dateFormat.format(today).substring(5);
        //查询当天是否为节日
        Festival festival = vipFestivalService.getBirthdayEqualsToday(todayStr);
        // 如果是，则群发邮件
        if(festival != null){
            //生产消息
            rabbitTemplate.convertAndSend("exchangetopic5","emaillist2",festival);
        }
    }
}
