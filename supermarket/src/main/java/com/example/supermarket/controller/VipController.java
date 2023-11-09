package com.example.supermarket.controller;


import com.example.supermarket.entity.dto.*;
import com.example.supermarket.service.VipService;
import com.example.supermarket.util.ResponseEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2023-10-13
 */
@Slf4j
@Controller
@RequestMapping("/vip")
public class VipController {

    @Autowired
    VipService vipService;
    @Autowired
    RabbitTemplate rabbitTemplate;

    //=================================================会员信息====================================================================
    //    添加会员
    @ResponseBody
    @PostMapping("/addVip")
    public Object addVip(@RequestBody @Valid VipDTO vipDto) {
        Integer hyId = vipService.addVip(vipDto);
        return new ResponseEntity(200, "添加会员成功", hyId);
    }

    //    查询所有会员
    @ResponseBody
    @PostMapping("/allVip")
    public Object allVip(@RequestBody PageDTO pageDto) {
        PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
        List<VipDTO> vips = vipService.allVip();
        //获取分页信息
        PageInfo pageInfo = new PageInfo(vips);
        return new ResponseEntity(200, "查询所有会员成功", pageInfo);
    }

    //    删除一行会员
    @ResponseBody
    @GetMapping("/removeVip/{hyId}")
    public Object removeVip(@PathVariable("hyId") String hyId) {
        vipService.removeVip(hyId);
        return new ResponseEntity(200, "删除会员成功", null);
    }

    //    删除多行会员
    @ResponseBody
    @PostMapping("/removeAllVip")
    public Object removeAllVip(@RequestBody List<Integer> ids) {
        vipService.removeAllVip(ids);
        return new ResponseEntity(200, "删除所有会员成功", null);
    }

    //    修改会员
    @ResponseBody
    @PostMapping("/updateVip")
    public Object updateVip(@RequestBody @Valid VipDTO vipDto) {
        vipService.updateVip(vipDto);
        return new ResponseEntity(200, "修改会员成功", null);
    }

    //    根据搜索条件查询会员
    @ResponseBody
    @PostMapping("/searchVip")
    public Object searchVip(@RequestBody VipSearchDTO vipSearchDto) {
        PageHelper.startPage(vipSearchDto.getPageNum(), vipSearchDto.getPageSize());
        List<VipSearchDTO> vips = vipService.searchVip(vipSearchDto);
        PageInfo pageInfo = new PageInfo(vips);
        return new ResponseEntity(200, "根据搜索条件查询会员成功", pageInfo);
    }

    //    excel表格导出数据
    @GetMapping("/export")
    public Object export(VipSearchDTO vipSearchDto, Map map) throws IOException {
        PageHelper.startPage(vipSearchDto.getPageNum(), vipSearchDto.getPageSize());
        List<VipSearchDTO> vips = vipService.searchVip(vipSearchDto);
        map.put("vips", vips);
        return "vipExport";
    }

    //    发送找回密码邮件
    @ResponseBody
    @GetMapping("/email")
    public Object email(@RequestParam("email") String email) {
        rabbitTemplate.convertAndSend("exchangetopic", "email", email);
        return new ResponseEntity(200, "发送邮件成功", null);
    }

    //   找回密码验证
    @ResponseBody
    @PostMapping("/findPassword")
    public Object findPassword(@RequestBody @Valid VipFindPasswordDTO vipFindPasswordDto) {
        VipDTO vipDto = vipService.findPassword(vipFindPasswordDto);
        return new ResponseEntity(200, "验证成功", vipDto.getGkEmail());
    }

    //    修改密码
    @ResponseBody
    @PostMapping("/findPassword2")
    public Object findPassword2(@RequestBody VipUpdatePasswordDTO vipUpdatePasswordDTO) {
        vipService.findPassword2(vipUpdatePasswordDTO);
        return new ResponseEntity(200, "会员修改密码成功", null);
    }

    //    充值余额
    @ResponseBody
    @PostMapping("/addBalance")
    public Object addBalance(@RequestBody @Valid VipBalanceDTO vipBalanceDTO) {
        vipService.addBalance(vipBalanceDTO);
        return new ResponseEntity(200, "充值余额成功", null);
    }

    //==========================================充值管理=============================================================================
//    插入充值记录
    @ResponseBody
    @PostMapping("/searchBalanceDetail")
    public Object searchBalanceDetail(@RequestBody VipBalanceSearchDTO vipBalanceSearchDTO) {
        PageHelper.startPage(vipBalanceSearchDTO.getPageNum(), vipBalanceSearchDTO.getPageSize());
        List<VipBalanceSearchDTO> vipBalanceDTOS = vipService.searchBalanceDetail(vipBalanceSearchDTO);
        PageInfo pageInfo = new PageInfo(vipBalanceDTOS);
        return new ResponseEntity(200, "查询所有余额明细成功", pageInfo);
    }

//    删除所有充值记录成功
    @ResponseBody
    @PostMapping("/removeAllBalanceDetail")
    public Object removeAllBalanceDetail(@RequestBody ArrayList<Integer> ids) {
        vipService.removeAllBalanceDetail(ids);
        return new ResponseEntity(200, "删除所有充值记录成功", null);
    }

//    删除充值记录
    @ResponseBody
    @GetMapping("/removeBalanceDetail/{czId}")
    public Object removeBalanceDetail(@PathVariable("czId") Integer czId) {
        vipService.removeBalanceDetail(czId);
        return new ResponseEntity(200, "删除充值记录成功", null);
    }

//    导出充值记录excel表
    @GetMapping("/exportVipBalance")
    public Object exportVipBalance(VipBalanceSearchDTO vipBalanceSearchDTO, Map map) throws IOException {
        PageHelper.startPage(vipBalanceSearchDTO.getPageNum(), vipBalanceSearchDTO.getPageSize());
        List<VipBalanceSearchDTO> vipBalanceSearchDTOS = vipService.searchBalanceDetail(vipBalanceSearchDTO);
        map.put("vipBalanceSearchDTOS", vipBalanceSearchDTOS);
        return "vipBalanceExport";
    }

    //===========================================积分管理===========================================================================
//    修改所有会员积分为指定转换率
    @ResponseBody
    @PostMapping("/updateAmountPointRatio/{amountPointRatio}")
    public Object updateAmountPointRatio(@PathVariable("amountPointRatio") Double amountPointRatio) {
        rabbitTemplate.convertAndSend("exchangetopic2", "amountPointRatio", amountPointRatio);
        return new ResponseEntity(200, "修改所有会员积分成功", null);
    }
//    查询会员等级
    @ResponseBody
    @GetMapping("/allVipLevels")
    public Object allVipLevels(){
        List<VipLevel> vipLevels = vipService.allVipLevels();
        return new ResponseEntity(200, "查询所有会员等级成功", vipLevels);
    }
//    删除会员等级
    @ResponseBody
    @GetMapping("/removeVipLevel/{djId}")
    public Object removeVipLevel(@PathVariable("djId") Integer djId){
        vipService.removeVipLevel(djId);
        return new ResponseEntity(200, "删除会员等级成功", null);
    }

//    修改会员等级
    @ResponseBody
    @PostMapping("/updateVipLevel")
    public Object updateVipLevel(@RequestBody VipLevel vipLevel){
        rabbitTemplate.convertAndSend("exchangetopic3", "pointLevelConvert", vipLevel);
        return new ResponseEntity(200, "修改会员等级信息成功", null);
    }
}

