package com.example.supermarket.controller;

import com.example.supermarket.entity.dto.VipExchangeDTO;
import com.example.supermarket.entity.dto.VipExchangeSearchDTO;
import com.example.supermarket.service.VipExchangeService;
import com.example.supermarket.util.ResponseEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 高垚淼
 * @version 1.0
 */
@RestController
@RequestMapping("/vipExchange")
public class VipExchangeController {

    @Autowired
    VipExchangeService vipExchangeService;
    
    @PostMapping("/add")
    public Object add(@RequestBody VipExchangeDTO vipExchangeDTO){
        vipExchangeService.add(vipExchangeDTO);
        return new ResponseEntity(200,"兑换商品成功",null);
    }

    @PostMapping("/search")
    public Object search(@RequestBody VipExchangeSearchDTO vipExchangeSearchDTO){
        PageHelper.startPage(vipExchangeSearchDTO.getPageNum(), vipExchangeSearchDTO.getPageSize());
        List<VipExchangeSearchDTO> vipExchangeSearchDTOS = vipExchangeService.search(vipExchangeSearchDTO);
        PageInfo pageInfo = new PageInfo(vipExchangeSearchDTOS);
        return new ResponseEntity(200, "根据搜索条件查询兑换记录成功", pageInfo);
    }

    @GetMapping("/remove/{dhId}")
    public Object remove(@PathVariable("dhId") Integer dhId){
        vipExchangeService.remove(dhId);
        return new ResponseEntity(200, "删除兑换记录成功", null);
    }
}
