package com.example.supermarket.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShopProductVO {
	private Integer mpId;

	private String mdName;

	private Integer mdId;
	private Integer spId;

	private String spName;
	private Integer putNo;
	private Integer putCount;

	private String unit;
	private String image;
	private String spPrice;
	private String spProduceDate;
	private  String putDate;

	private  Integer status;
	private int[] spIds;

}
