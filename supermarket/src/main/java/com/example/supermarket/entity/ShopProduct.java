package com.example.supermarket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShopProduct {
	private Integer mpId;
	private Integer mdId;
	private Integer spId;
	private int putNo;
	private int putCount;
	private  String putDate;
	private String unit;
	private int spPrice;
	private Integer status;
	private int[] spIds;

}
