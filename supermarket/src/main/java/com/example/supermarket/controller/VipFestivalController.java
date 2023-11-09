package com.example.supermarket.controller;

import com.example.supermarket.entity.Festival;
import com.example.supermarket.service.VipFestivalService;
import com.example.supermarket.util.ResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 高垚淼
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/festival")
public class VipFestivalController {

    @Autowired
    VipFestivalService vipFestivalService;

    @GetMapping("/all")
    public Object all(){
        List<Festival> festivals = vipFestivalService.all();
        return new ResponseEntity(200,"查询所有节日信息成功",festivals);
    }

    @PostMapping("/add")
    public Object add(@RequestBody Festival festival){
        vipFestivalService.add(festival);
        return new ResponseEntity(200,"节日添加成功",null);
    }

    @PostMapping("/update")
    public Object update(@RequestBody Festival festival){
        vipFestivalService.update(festival);
        return new ResponseEntity(200,"节日修改成功",null);
    }

    @GetMapping("/remove/{viId}")
    public Object remove(@PathVariable("viId") Integer viId){
        vipFestivalService.remove(viId);
        return new ResponseEntity(200,"节日删除成功",null);
    }



}
