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
public class Permission{

    private Integer pid;

    private String pname;

    private String ppath;

    private Integer ppid;

    private Integer isdelete;

    private List<Permission> children = new ArrayList<>();
}
