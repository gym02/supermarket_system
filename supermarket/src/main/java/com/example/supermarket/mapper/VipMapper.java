package com.example.supermarket.mapper;

import com.example.supermarket.entity.Customer;
import com.example.supermarket.entity.Vip;
import com.example.supermarket.entity.dto.*;
import org.apache.ibatis.annotations.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2023-10-13
 */
@Mapper
public interface VipMapper {

//=============================================会员信息==========================================================================

    @Select("select gk_id from customer where gk_name = #{hyName} and gk_email = #{gkEmail} and isdelete=0")
    Integer selectGk(@Param("hyName") String hyName, @Param("gkEmail") String gkEmail);

    @Insert("insert into vip values (#{gkId},#{hyId},#{hyName},#{hyUsername},#{hyPassword}," +
            "#{hyLevel},#{hyDiscount},#{hyBalance},#{hyPoint},0)")
    void addVip(VipDTO vipDto);

    @Select("select v.gk_id, v.hy_id,v.hy_name, c.gk_email,v.hy_username, v.hy_level, v.hy_discount, v.hy_balance, v.hy_point ,v.hy_totalpoint" +
            " from vip v,customer c where v.gk_id = c.gk_id and v.isdelete=0")
    List<VipDTO> allVip();

    @Update("update vip set isdelete=1 where hy_id = #{hyId}")
    void removeVip(String hyId);

    void removeAllVip(@Param("ids") List<Integer> ids);

    //    检查修改的会员名是否存在
    @Select("select * from customer where gk_name = #{hyName} and isdelete = 0")
    Customer checkVipName(VipDTO vipDto);

    @Update("update vip set hy_name=#{hyName},hy_balance=#{hyBalance},hy_point=#{hyPoint},hy_totalpoint=#{hyTotalPoint} where hy_id=#{hyId}")
    void updateVip(VipDTO vipDto);

    //    修改该会员对应顾客邮箱
    @Update("update customer set gk_email=#{gkEmail} where gk_id=#{gkId}")
    void updateCustomer(VipDTO vipDto);

    List<VipSearchDTO> searchVip(VipSearchDTO vipSearchDto);

    @Select("select * from vip v ,customer c where v.gk_id = c.gk_id and v.hy_name = #{hyName} and c.gk_email = #{gkEmail}" +
            " and v.isdelete = 0")
    VipDTO selectVipByNameAndEmail(VipFindPasswordDTO vipFindPasswordDto);

    //    根据邮箱查询用户id
    @Select("select gk_id from customer where gk_email = #{gkEmail} and isdelete = 0")
    Integer selectGkByEmail(String gkEmail);

    //    根据用户id修改会员卡密码
    @Update("update vip  set hy_password = #{vipUpdatePasswordDTO.pass} where gk_id = #{gkId}")
    void findPassword2(@Param("vipUpdatePasswordDTO") VipUpdatePasswordDTO vipUpdatePasswordDTO, @Param("gkId") Integer gkId);

    //    余额充值前查询卡号是否存在
    @Select("select * from vip where hy_id = #{hyId} and isdelete = 0")
    Vip searchVipById(com.example.supermarket.controller.@Valid VipBalanceDTO vipBalanceDTO);

    //    对会员账号进行余额充值
    @Update("update vip set hy_balance = hy_balance+#{hyBalance} where hy_id = #{hyId}")
    void addBalance(com.example.supermarket.controller.@Valid VipBalanceDTO vipBalanceDTO);

//=========================================充值记录=======================================================================

    //    插入充值信息
    @Insert("insert into vipbalance values(null,#{hyId},#{hyName},#{czAmount},#{czTime}," +
            "#{czType},#{czStatue},0)")
    void insertAddBalanceInformation(VipBalanceDTO vipBalance);

    //    查询所有充值记录
    List<VipBalanceSearchDTO> searchBalanceDetail(VipBalanceSearchDTO vipBalanceSearchDTO);

    //    删除所有充值记录
    void removeAllBalanceDetail(@Param("ids") ArrayList<Integer> ids);

    //    删除充值记录
    @Update("update vipbalance set isdelete = 1 where cz_id = #{czId}")
    void removeBalanceDetail(Integer czId);

    //    根据换算率，重新计算会员剩余积分
    void dealAmoutPointRatio(Double amountPointRatio);

//    根据换算率，重新计算会员总积分
    void dealAmoutPointRatioTotal(Double amountPointRatio);

    //    查询所有会员等级
    @Select("select * from viplevel where isdelete = 0")
    List<VipLevel> allVipLevels();

    //    删除会员等级
    @Update("update viplevel set isdelete = 1 where dj_id = #{djId}")
    void removeVipLevel(Integer djId);

//    修改会员等级表中，会员等级信息
    void updateVipLevel(VipLevel vipLevel);

//    修改会员表中，所有会员的等级
    void updateAllVipLevel(VipLevel vipLevel);

//    修改会员表中，所有的会员等级，如果max为Null
    void updateAllVipLevelIfNull(VipLevel vipLevel);

//    查询生日在当天的所有会员的邮箱
    @Select("select distinct * from vip v, customer c where v.gk_id = c.gk_id and #{todayStr} = substring(c.gk_birthday,6,5) and v.isdelete = 0")
    List<VipDTO> getBirthdayEqualsToday(String todayStr);

//    判断用户是否已经注册
    @Select("select hy_id from vip v,customer c where v.gk_id = c.gk_id and gk_email = #{gkEmail} and v.isdelete = 0")
    Integer searchVipExits(String gkEmail);

}
