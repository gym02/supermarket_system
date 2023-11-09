package com.example.supermarket.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StoreDTO {
    private Integer cgId;
    private Integer whId;
    private int pageSize;
    private int pageNum;
}
