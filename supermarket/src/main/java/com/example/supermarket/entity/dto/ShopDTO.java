package com.example.supermarket.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShopDTO {



	private String mdName;

	private String mdAddress;

	private String mdPhone;

	private String mdCheif;
	private Integer isdelete;

}
