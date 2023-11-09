package com.example.supermarket.entity.vo;

import com.example.supermarket.entity.Customer;
import com.example.supermarket.entity.Kind;
import com.example.supermarket.entity.SendWay;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndentVO {

    private String ddId;//订单号

    private ProductVO productVO;//商品信息---->商品引用

    private Kind kind;//商品类型

    private Integer ddNum;//购买数量

    @NotNull(message = "买家信息不能未空")
    private Customer customer;//买家姓名---->顾客引用

    private String ddPayway;//支付方式

    private String ddOrdertime;//下单时间

    private SendWay sendWay;//送货方式---->送货引用

    private String ddStatus;//交易状态

    private String ddType;//支付状态

    private double ddYunfei;//运费

    private String ddComment;//订单备注

    private String xdDate;//下单时间

    private String fkDate;//付款时间

    private String fhDate;//发货时间

    private String shDate;//收货时间

    private String wcDate;//完成时间
 }
