package com.example.supermarket.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2023-10-13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Supply{

    private Integer suId;
    @NotBlank(message = "suName--NotBlank" )
    @Size(min = 3, max = 6,message = "suName{min}-{max}")
    private String suName;

    private String suContactor;
    @NotBlank(message = "suPhone--NotBlank" )
    @Size(min = 11, max = 12,message = "suPhone{min}-{max}")
    private String suPhone;

    private Integer isdelete;

    private int[]suIds;


}
