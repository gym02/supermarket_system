package com.example.supermarket.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SupplyDTO {

	private String suName;

	private String suContactor;

	private String suPhone;

	private Integer isdelete;

}
