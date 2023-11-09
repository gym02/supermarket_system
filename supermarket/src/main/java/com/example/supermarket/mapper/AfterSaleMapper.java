package com.example.supermarket.mapper;

import com.example.supermarket.entity.dto.AfterSaleDTO;
import com.example.supermarket.entity.dto.AfterSaleTypeDTO;
import com.example.supermarket.entity.vo.AfterSaleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface AfterSaleMapper {

    List<AfterSaleVO> getAllAfterSale(AfterSaleDTO afterSaleDTO);

    @Update("update aftersale set as_status = #{asStatus}, jc_date = #{timeNow} where dd_id = #{ddId}")
    void agree(@Param("ddId") String ddId, @Param("asStatus")String asStatus, @Param("timeNow") String timeNow);

    @Update("update aftersale set as_status = #{asStatus} , jc_date = #{timeNow} where dd_id = #{ddId}")
    void dealRequest1(@Param("ddId") String ddId, @Param("asStatus")String asStatus, @Param("timeNow") String timeNow);

    @Update("update aftersale set as_status = #{asStatus} , th_date = #{timeNow} where dd_id = #{ddId}")
    void dealRequest2(@Param("ddId")String ddId, @Param("asStatus")String asStatus, @Param("timeNow")String timeNow);

    @Update("update aftersale set as_status = '完成' , fin_date = #{timeNow} where dd_id = #{ddId}")
    void finAfterSale(@Param("ddId")String ddId, @Param("timeNow")String timeNow);

    @Update("update aftersale set as_type = #{asType}, as_status = #{asStatus}," +
            "as_reason = #{asReason}, as_explain = #{asExplain} where dd_id = #{ddId}")
    void changeAfterSaleType(AfterSaleTypeDTO afterSaleTypeDTO);

    @Update("update indent set dd_status = '退货退款' where dd_id = #{ddId} ")
    void changeIndentDdStatus(String ddId);

    @Update("update indent set dd_status = '已驳回' where dd_id = #{ddId} ")
    void reject(String ddId, String timeNow);
}
