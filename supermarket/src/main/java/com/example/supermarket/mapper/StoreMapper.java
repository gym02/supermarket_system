package com.example.supermarket.mapper;

import com.example.supermarket.entity.User;
import com.example.supermarket.entity.WareHouse;
import com.example.supermarket.entity.dto.StoreDTO;
import com.example.supermarket.entity.vo.StoreVO;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2023-10-13
 */
@Mapper
public interface StoreMapper {
@Update("update store set cg_count=cg_count+#{cgCount} where cg_id=#{cargo.cargoId} and wh_id=#{wareHouse.whId} ")
    int updateStore(StoreVO storeVO);
@Select("select * from user where user_id=#{userId} and user_pwd=#{userPwd}")
    User getUserByPwd(@Param("userId") String userId,@Param("userPwd") String userPwd);

    List<StoreVO> getStore(StoreDTO storeDTO);
@Select("select * from warehouse where wh_id=#{whId}")
    WareHouse getWareHouseById(int whId);
@Insert("insert into store values (#{cargo.cargoId},#{cgUnit},#{wareHouse.whId},0,#{cgCount})")
    void insertStore(StoreVO storeVO);
@Select("select c.cargo_name name,s.cg_count value  from store s ,cargo c where s.cg_id=c.cargo_id and s.isdelete=0 and wh_id=#{whId} ")
   List< Map<String, String>> getMaps(int whId);


}
