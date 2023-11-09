package com.example.supermarket.mapper;

import com.example.supermarket.entity.Festival;
import com.example.supermarket.entity.dto.VipDTO;
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
public interface VipFestivalMapper {

    @Select("select * from festival where isdelete = 0")
    List<Festival> all();

    @Insert("insert into festival values (null,#{jrName},#{jrBless},#{jrTime},0)")
    void add(Festival festival);

    @Update("update festival set jr_name = #{jrName},jr_bless = #{jrBless},jr_time = #{jrTime} where jr_id = #{jrId}")
    void update(Festival festival);


    @Update("update festival set isdelete = 1 where jr_id = #{jrId}")
    void remove(Integer jrId);

    @Select("select * from festival where isdelete = 0 and #{todayStr} = jr_time")
    Festival getBirthdayEqualsToday(String todayStr);

    @Select("select * from vip v,customer c where v.gk_id = c.gk_id and v.isdelete = 0")
    List<VipDTO> getVips();
}
