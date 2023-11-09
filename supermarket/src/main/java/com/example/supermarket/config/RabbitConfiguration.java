package com.example.supermarket.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * @author 高垚淼
 * @version 1.0
 */
@Configuration
public class RabbitConfiguration {


    @Bean
    public Jackson2JsonMessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }



    //声明队列，根据转换率重新修改会员积分
    @Bean
    public Queue AmountPointRatio(){
        return new Queue("amountPointRatio");
    }

    //topisc模式使用topic类型的交换机
    @Bean
    public TopicExchange exchangetopic2(){
        return new TopicExchange("exchangetopic2");
    }

    //绑定交换机和路由
    @Bean
    public Binding bindingqueueemailtoexchangetopic2(@Qualifier("AmountPointRatio") Queue amountPointRatio, TopicExchange exchangetopic2){
        return BindingBuilder.bind(amountPointRatio).to(exchangetopic2).with("amountPointRatio");
    }

    //声明队列，根据修改的会员等级映射，修改会员vip等级
    @Bean
    public Queue PointLevelConvert(){
        return new Queue("pointLevelConvert");
    }

    //topisc模式使用topic类型的交换机
    @Bean
    public TopicExchange exchangetopic3(){
        return new TopicExchange("exchangetopic3");
    }

    //绑定交换机和路由
    @Bean
    public Binding bindingqueueemailtoexchangetopic3(@Qualifier("PointLevelConvert") Queue pointLevelConvert, TopicExchange exchangetopic3){
        return BindingBuilder.bind(pointLevelConvert).to(exchangetopic3).with("pointLevelConvert");
    }

    //声明队列，发送批量邮箱，祝福会员生日
    @Bean
    public Queue Emaillist(){
        return new Queue("emaillist");
    }

    //topisc模式使用topic类型的交换机
    @Bean
    public TopicExchange exchangetopic4(){
        return new TopicExchange("exchangetopic4");
    }

    //绑定交换机和路由
    @Bean
    public Binding bindingqueueemailtoexchangetopic4(@Qualifier("Emaillist")Queue emaillist, TopicExchange exchangetopic4){
        return BindingBuilder.bind(emaillist).to(exchangetopic4).with("emaillist");
    }

    //声明队列，发送批量邮箱，祝福节日快乐
    @Bean
    public Queue Emaillist2(){
        return new Queue("emaillist2");
    }

    //topisc模式使用topic类型的交换机
    @Bean
    public TopicExchange exchangetopic5(){
        return new TopicExchange("exchangetopic5");
    }

    //绑定交换机和路由
    @Bean
    public Binding bindingqueueemailtoexchangetopic5(@Qualifier("Emaillist2")Queue emaillist2, TopicExchange exchangetopic5){
        return BindingBuilder.bind(emaillist2).to(exchangetopic5).with("emaillist2");
    }
}
