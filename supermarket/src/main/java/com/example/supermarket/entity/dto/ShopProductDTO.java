package com.example.supermarket.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShopProductDTO {
	private Integer mdId;
	private Integer spId;
	private int putNo;
	private  String putDate;
	private int[] spIds;

}
