package com.example.supermarket.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlowDTO {

    private String orderId;//订单id

    private String name;//收货姓名

    private String phone;//收货电话

    private String location;//收货地址

    private String otherInfo;//备注

    private Integer id;//用户id
}
