package com.example.supermarket.entity.dto;

import com.example.supermarket.entity.SendWay;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndentDTO {
    //订单号
    private String ddId;
    //商品名称
    private String spName;
    //买家名称
    private String mjName;
    //送货方式
    private SendWay sendWay;
    //订单状态
    private String ddStatus;
    //下单时间
    private String from;//开始时间
    private String to;//结束时间
    //分页信息
    private int pageIndex;
    private int pageNum;

}
