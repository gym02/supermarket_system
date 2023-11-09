package com.example.supermarket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WareHouse {
    private Integer whId;
    private String whName;
    private  Integer whPid;
    private List<WareHouse> children=new ArrayList<>();

}
