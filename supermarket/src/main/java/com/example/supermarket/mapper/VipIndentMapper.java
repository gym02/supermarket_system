package com.example.supermarket.mapper;

import com.example.supermarket.entity.VipIndent;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author 高垚淼
 * @version 1.0
 */
@Mapper
public interface VipIndentMapper {

    @Select("select * from vipindent where isdelete = 0")
    List<VipIndent> all();

    @Insert("insert into vipindent values (null,#{viName},#{viPoint},#{viNumber},0)")
    void add(VipIndent vipIndent);

    @Update("update vipindent set vi_name = #{viName},vi_point = #{viPoint},vi_number = #{viNumber} where vi_id = #{viId}")
    void update(VipIndent vipIndent);


    @Update("update vipindent set isdelete = 1 where vi_id = #{viId}")
    void remove(Integer viId);


}
