package com.example.supermarket.mapper;

import com.example.supermarket.entity.Cargo;
import com.example.supermarket.entity.WareHouse;
import com.example.supermarket.entity.dto.GoodsDTO;
import com.example.supermarket.entity.vo.GoodsVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GoodsMapper {
    List<GoodsVO> getGoods(GoodsDTO goodsDTO);
@Select("select * from cargo ")
    List<Cargo> getCargos();
    @Insert("insert into goods values (null,#{cargo.cargoId},#{goodsCount},#{goodsUnit},#{goodsNumber},#{goodsfromDate},#{user.userId},0,1) ")
    void addGoods(GoodsVO goodsVO);
    @Update("update goods set isdelete = 1 where storeapply_id = #{storeapplyId}")
    void delete(int storeapplyId);
    @Update("update goods set goods_id=#{cargo.cargoId},goodsfrom_date=#{goodsfromDate},goods_count=#{goodsCount} ,goods_unit=#{goodsUnit},goods_state=#{goodsState} where storeapply_id=#{storeapplyId}")
    void update(GoodsVO goodsVO);

    void deleteGoodsBatch(@Param("storeapplyIds") int[] storeapplyIds);

    GoodsVO getGoodsById(int storeapplyId);
@Select("select * from warehouse where isdelete=0")
    List<WareHouse> selectAllWarehouse();
}
