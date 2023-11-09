package com.example.supermarket.service;

import com.example.supermarket.controller.VipBalanceDTO;
import com.example.supermarket.entity.dto.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2023-10-13
 */
public interface VipService  {

    Integer addVip(VipDTO vipDto);

    List<VipDTO> allVip();

    void removeVip(String hyId);

    void removeAllVip(List<Integer> ids);

    void updateVip(VipDTO vipDto);

    List<VipSearchDTO> searchVip(VipSearchDTO vipSearchDto);

    VipDTO findPassword(VipFindPasswordDTO vipFindPasswordDto);

    void findPassword2(VipUpdatePasswordDTO vipUpdatePasswordDTO);

    void addBalance(@Valid VipBalanceDTO vipBalanceDTO);

    List<VipBalanceSearchDTO> searchBalanceDetail(VipBalanceSearchDTO vipBalanceSearchDTO);

    void removeAllBalanceDetail(ArrayList<Integer> ids);

    void removeBalanceDetail(Integer czId);

    List<VipLevel> allVipLevels();

    void removeVipLevel(Integer djId);

    void updateVipLevel(VipLevel vipLevel);

    List<VipDTO> getBirthdayEqualsToday(String todayStr);

    void dealAmoutPointRatio(Double amountPointRatio);
}
