package com.example.supermarket.service.impl;

import com.example.supermarket.entity.Customer;
import com.example.supermarket.entity.Vip;
import com.example.supermarket.entity.dto.*;
import com.example.supermarket.exception.*;
import com.example.supermarket.mapper.VipMapper;
import com.example.supermarket.service.VipService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2023-10-13
 */
@Slf4j
@Service
public class VipServiceImpl implements VipService {

    @Autowired
    VipMapper vipMapper;
    @Autowired
    RedisTemplate redisTemplate;

//============================================会员信息==========================================================================
    //    增加会员
    @Transactional
    @Override
    public Integer addVip(VipDTO vip) {
        Long time = System.currentTimeMillis();
        Integer hyId = Integer.parseInt(time.toString().substring(5, 12));
        vip.setHyId(hyId);
        vip.setHyUsername(hyId);
        vip.setHyLevel(1);
        vip.setHyDiscount(0.95);
        vip.setHyPoint(0);
        Integer gk_id = vipMapper.selectGk(vip.getHyName(), vip.getGkEmail());
        //判断用户是否存在
        if (gk_id == null) {
            throw new CustomerNotFundException();
        }
        //判断是否已注册
        Integer hy_id = vipMapper.searchVipExits(vip.getGkEmail());
        if(hy_id != null){
            throw new VipIsExitsException();
        }
        vip.setGkId(gk_id);
        vipMapper.addVip(vip);
        return hyId;
    }

    //    查询所有会员
    @Override
    public List<VipDTO> allVip() {
        return vipMapper.allVip();
    }

    //    删除指定会员
    @Override
    public void removeVip(String hyId) {
        vipMapper.removeVip(hyId);
    }

    //    删除批量会员
    @Override
    public void removeAllVip(List<Integer> ids) {
        if (ids.size() == 0) {
            throw new NullVipIdsException();
        }
        vipMapper.removeAllVip(ids);
    }

    //    修改会员
    @Transactional
    @Override
    public void updateVip(VipDTO vipDto) {
        Customer customer = vipMapper.checkVipName(vipDto);
        if (customer == null) {
            throw new CustomerNotFundException();
        }
        vipMapper.updateVip(vipDto);
        vipMapper.updateCustomer(vipDto);
    }

    //    根据搜索条件查询会员
    @Override
    public List<VipSearchDTO> searchVip(VipSearchDTO vipSearchDto) {
        return vipMapper.searchVip(vipSearchDto);
    }

    //    根据信息验证密码修改合法性
    @Override
    public VipDTO findPassword(VipFindPasswordDTO vipFindPasswordDto) {
        VipDTO vipDto = vipMapper.selectVipByNameAndEmail(vipFindPasswordDto);
        if (vipDto == null) {
            throw new CustomerNotFundException();
        }
        if (!vipFindPasswordDto.getCode().equals(redisTemplate.opsForValue().get("code"))) {
            throw new FindPasswordCodeErrorException();
        }
        return vipDto;
    }

    //    修改密码
    @Override
    public void findPassword2(VipUpdatePasswordDTO vipUpdatePasswordDTO) {
        if (!vipUpdatePasswordDTO.getPass().equals(vipUpdatePasswordDTO.getCheckPass())) {
            throw new CheckPasswordNotSameException();
        }
        Integer gkId = vipMapper.selectGkByEmail(vipUpdatePasswordDTO.getGkEmail());
        vipMapper.findPassword2(vipUpdatePasswordDTO, gkId);
    }

    //    充值余额,并记录充值信息
    @Transactional
    @Override
    public void addBalance(com.example.supermarket.controller.@Valid VipBalanceDTO vipBalanceDTO) {
        //查询卡号是否存在
        Vip vip = vipMapper.searchVipById(vipBalanceDTO);
        if (vip == null) throw new VipIsNullException();
        //对该开号进行充值
        vipMapper.addBalance(vipBalanceDTO);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //记录充值信息
        VipBalanceDTO vipBalance = new VipBalanceDTO(null, vipBalanceDTO.getHyId(), vip.getHyName(),
                vipBalanceDTO.getHyBalance(), vipBalanceDTO.getHyBalance(), simpleDateFormat.format(new Date()),
                vipBalanceDTO.getCzType(), "成功", 0);
        vipMapper.insertAddBalanceInformation(vipBalance);
    }

//=============================================充值信息=====================================================================
    //    查询所有充值记录
    @Override
    public List<VipBalanceSearchDTO> searchBalanceDetail(VipBalanceSearchDTO vipBalanceSearchDTO) {
        return vipMapper.searchBalanceDetail(vipBalanceSearchDTO);
    }
// 批量删除充值记录信息
    @Override
    public void removeAllBalanceDetail(ArrayList<Integer> ids) {
        if (ids.size() == 0) {
            throw new NullVipIdsException();
        }
        vipMapper.removeAllBalanceDetail(ids);
    }

//    删除充值记录信息
    @Override
    public void removeBalanceDetail(Integer czId) {
        vipMapper.removeBalanceDetail(czId);
    }

//===============================================积分管理======================================================================
//    查询所有会员等级
    @Override
    public List<VipLevel> allVipLevels() {
        return vipMapper.allVipLevels();
    }

//    删除会员等级
    @Override
    public void removeVipLevel(Integer djId) {
        vipMapper.removeVipLevel(djId);
    }

//    修改会员等级
    @Override
    public void updateVipLevel(VipLevel vipLevel) {
        vipMapper.updateVipLevel(vipLevel);
    }

//    查询会员生日在当天的所有会员信息
    @Override
    public List<VipDTO> getBirthdayEqualsToday(String todayStr) {
        return vipMapper.getBirthdayEqualsToday(todayStr);
    }

//    根据消费金额和积分的换算，重新计算会员剩余积分和总积分
    @Override
    public void dealAmoutPointRatio(Double amountPointRatio) {
        vipMapper.dealAmoutPointRatio(amountPointRatio);
        vipMapper.dealAmoutPointRatioTotal(amountPointRatio);
    }

}

