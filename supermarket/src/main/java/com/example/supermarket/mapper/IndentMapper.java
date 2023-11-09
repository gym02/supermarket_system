package com.example.supermarket.mapper;

import com.example.supermarket.entity.Kind;
import com.example.supermarket.entity.Product;
import com.example.supermarket.entity.SendWay;
import com.example.supermarket.entity.dto.FlowDTO;
import com.example.supermarket.entity.dto.IndentDTO;
import com.example.supermarket.entity.dto.OrderDTO;
import com.example.supermarket.entity.vo.IndentVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface IndentMapper{

    List<IndentVO> getAllIndent(IndentDTO indentDTO);

    @Select("select * from sendway")
    List<SendWay> getAllSendWay();

    @Select("select * from kind")
    List<Kind> getAllKind();

    @Update("update indent set dd_status = #{type} where dd_id = #{ddId}")
    void changeDdStatus(@Param("ddId") String ddId, @Param("type") String type);

    @Insert("insert into indent values (#{ddId},#{ddOrdertime},#{num},#{ddPaystatus}," +
            "#{payWay},#{freight},#{delivertime},#{ddStatus},#{ddType},#{otherInfo}," +
            "null,#{product.spId},#{sendWay.shId},0)")
    void addNewOrder(OrderDTO orderDTO);

    @Update("update indent set isdelete = 1 where dd_id = #{ddId}")
    void deleteByDdId(String ddId);

    @Select("select dd_type from indent where dd_id = #{ddId} ")
    String selectDdTypeByDdId(String ddId);

    @Update("update indent set dd_status = '订单完成',dd_type = '已完成' where dd_id = #{ddId}")
    void finishIndentByDdId(String ddId);

    @Select("select gk_id from indent where dd_id = #{orderId}")
    int getCustomerIdByDdId(String orderId);

    @Update("update customer set gk_name=#{name},gk_phone=#{phone},gk_location=#{location}" +
            "where gk_id=#{id}")
    void modifyFlowInfo(FlowDTO flowDTO);

    @Update("update indent set dd_comment = #{otherInfo} where dd_id = #{orderId}")
    void modifyFlowInfo1(FlowDTO flowDTO);

    @Select("select  * from product where zl_id = #{zlId}")
    List<Product> getAllProductNameByZlId(Integer zlId);

    @Select("select sp_price from product where sp_id = #{spId}")
    double getPriceByProductName(Integer spId);

    @Select("select gk_id from customer where gk_name = #{name} and gk_phone = #{phone}")
    Integer selectCustomer(@Param("name") String name, @Param("phone")String phone);

    @Select("select LAST_INSERT_ID()")
    int getLastInsertId();

    @Insert("insert into customer values (null,#{name},#{phone},#{location},#{email},#{birthday},0)")
    void addNewCustomer(OrderDTO orderDTO);

    @Update("update indent set gk_id = #{gkId} where dd_id = #{ddId}")
    void insertNewGkId(@Param("ddId") String ddId, @Param("gkId")int gkId);

    @Update("update aftersale set fh_date = #{time} where dd_id = #{ddId}")
    void addNewFhDate(@Param("ddId") String ddId, @Param("time") String time);

    @Update("update aftersale set fk_date = #{time} where dd_id = #{ddId}")
    void addNewFkDate(@Param("ddId")String ddId, @Param("time")String time);

    @Update("update aftersale set sh_date = #{time} where dd_id = #{ddId}")
    void addNewShDate(String ddId, String time);

    @Update("update aftersale set wc_date = #{time} where dd_id = #{ddId}")
    void addNewWcDate(String ddId, String time);

    @Insert("insert into aftersale values (#{str}, #{ddId}, #{time},null,null,null,null,null,0)")
    void addNewAfterSale(@Param("str") String str, @Param("ddId") String ddId, @Param("time") String time);
}
