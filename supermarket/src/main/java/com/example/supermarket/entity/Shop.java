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
public class Shop{

    private int[] mdIds;
    private  Integer mdId;
    @NotBlank(message = "mdName--NotBlank" )
    @Size(min = 3, max = 15,message = "mdName{min}-{max}")
    private String mdName;
    @NotBlank(message = "mdAddress--NotBlank" )
    @Size(min = 3, max = 15,message = "mdAddress{min}-{max}")
    private String mdAddress;
    @NotBlank(message = "mdPhone--NotBlank" )
    @Size(min = 11, max = 12,message = "mdPhone{min}-{max}")
    private String mdPhone;

    private String mdCheif;

    private String mdLicenseSrc;

    private String mdDescribe;

    private Integer isdelete;


}
