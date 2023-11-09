package com.example.supermarket.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author 高垚淼
 * @version 1.0
 */
@Mapper
public interface VipDataMapper {

    //    获取所有会员等级
    @Select("SELECT dj_name name, COUNT(*) AS value FROM vip,viplevel where vip.hy_level = viplevel.dj_id and vip.isdelete = 0 GROUP BY hy_level")
    List<Map<String, Integer>> getLevels();

//    获取不同月份余额充值人数
    @Select("select count(*) from vipbalance where isdelete = 0 group by substring(cz_time,6,2)")
    List<Integer> BalanceNumber();

//    查询积分商品兑换数量
    @Select("select vi_name name,count(*) number from vipindent , vipexchange where vipindent.vi_id = vipexchange.vi_id and " +
            "  vipexchange.isdelete = 0 GROUP BY vi_name")
    List<Map<String,Integer>> PointNumber();

//    查询当天是否是节日
    @Select("select jr_name from festival where jr_time = #{substring} and isdelete = 0")
    String getFestival(String substring);

//    总会员数
    @Select("select count(*) from vip where isdelete = 0")
    Integer getVipNumber();

//    当天生日会员总数
    @Select("select COUNT(*)  FROM vip v,customer c WHERE v.gk_id = c.gk_id and v.isdelete = 0 and #{substring} like SUBSTRING(c.gk_birthday,6,5)  ")
    Integer getBirthdayNumber(String substring);
}
