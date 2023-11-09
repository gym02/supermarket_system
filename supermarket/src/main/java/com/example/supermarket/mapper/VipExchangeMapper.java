package com.example.supermarket.mapper;

import com.example.supermarket.entity.dto.VipExchangeDTO;
import com.example.supermarket.entity.dto.VipExchangeSearchDTO;
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
public interface VipExchangeMapper {


    //    查询会员剩余积分
    @Select("select hy_point from vip where hy_id = #{hyId} and isdelete = 0")
    Integer selectHyPoint(VipExchangeDTO vipExchangeDTO);

    //    查询这次兑换的所有相关信息
    @Select("select distinct * from vip ,vipindent where hy_id = #{hyId} and vi_id = #{viId}")
    VipExchangeDTO selectAllMessage(VipExchangeDTO vipExchangeDTO);

    //    插入兑换记录
    @Insert("insert into vipexchange values (null,#{viId},#{hyId},#{dhTime},0)")
    void add(VipExchangeDTO vipExchangeDTO);

    //    修改积分商品数量
    @Update("update vipindent set vi_number = vi_number - 1 where vi_id = #{viId}")
    void decreasePointGoods(Integer viId);

    //    修改会员剩余积分
    @Update("update vip set hy_point = hy_point - #{viPoint} where hy_id = #{hyId}")
    void decreasePoint(VipExchangeDTO all);

    //    查询所有兑换信息
    List<VipExchangeSearchDTO> search(VipExchangeSearchDTO vipExchangeSearchDTO);

//    删除兑换信息
    @Update("update vipexchange set isdelete = 1 where dh_id = #{dhId}")
    void remove(Integer dhId);
}
