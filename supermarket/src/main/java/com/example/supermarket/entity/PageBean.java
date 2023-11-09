package com.example.supermarket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageBean<T> {
    private int pageNum;
    private int pageSize;//每页的大小
    private int totalRecord;//总条数
    private int totalPage;//总页数
    private List<T> beanList;//当前页数据

}
