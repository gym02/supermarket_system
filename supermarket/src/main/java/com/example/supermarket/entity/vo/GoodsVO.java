package com.example.supermarket.entity.vo;

import com.example.supermarket.entity.Cargo;
import com.example.supermarket.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsVO {
    private Integer storeapplyId;
    private Cargo cargo;
    private Integer goodsCount;
    private String goodsUnit;
    private String goodsNumber;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date  goodsfromDate;
    private User user;
    private Integer us;
    private Integer goodsState;

}
