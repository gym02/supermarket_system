package com.example.supermarket.entity;



import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;


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
public class Menu{
    private Integer mid;
    private String mname;
    private Integer pmid;
    private String mpath;
    private Integer isdelete;
    private List<Menu> children = new ArrayList<>();
}
