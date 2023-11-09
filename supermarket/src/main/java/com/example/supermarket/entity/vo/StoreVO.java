package com.example.supermarket.entity.vo;

import com.example.supermarket.entity.Cargo;
import com.example.supermarket.entity.WareHouse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreVO {

    private Cargo cargo;

    private String cgUnit;
    private int cgCount;
    private WareHouse wareHouse;

}
