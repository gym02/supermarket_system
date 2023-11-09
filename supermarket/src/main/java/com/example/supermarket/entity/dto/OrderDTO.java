package com.example.supermarket.entity.dto;

import com.example.supermarket.entity.Kind;
import com.example.supermarket.entity.Product;
import com.example.supermarket.entity.SendWay;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private String ddId;//订单id------>自动生成

    private String ddOrdertime;//下单时间------>自动生成

    private Integer num; //商品数量

    private String ddPaystatus;//支付状态------>自动生成

    private String payWay;//支付方式

    private double freight;//运费

    private String delivertime;//发货时间------>自动生成，下单时间+1天

    private String ddStatus;//订单状态------>自动生成

    private String ddType;//交易状态------>自动生成

    private String otherInfo;//备注

    private String name;//消费者名称

    private String phone;//消费者电话

    private String location;//消费者地址

    private String email;//消费者邮箱

    private String birthday;//消费者生日

    private Product product;//商品信息，名称，单价

    private SendWay sendWay;//送货信息

    private Kind kind;//商品种类
}
