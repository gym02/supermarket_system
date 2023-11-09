package com.example.supermarket.controller;

import com.example.supermarket.entity.VipIndent;
import com.example.supermarket.service.VipIndentService;
import com.example.supermarket.util.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 高垚淼
 * @version 1.0
 */
@RestController
@RequestMapping("/vipIndent")
public class VipIndentConttroller {
    @Autowired
    VipIndentService vipIndentService;

    @GetMapping("/all")
    public Object all(){
        List<VipIndent> vipIndents = vipIndentService.all();
        return new ResponseEntity(200,"查询所有积分商品信息成功",vipIndents);
    }

    @PostMapping("/add")
    public Object add(@RequestBody VipIndent vipIndent){
        vipIndentService.add(vipIndent);
        return new ResponseEntity(200,"商品添加成功",null);
    }

    @PostMapping("/update")
    public Object update(@RequestBody VipIndent vipIndent){
        vipIndentService.update(vipIndent);
        return new ResponseEntity(200,"商品修改成功",null);
    }

    @GetMapping("/remove/{viId}")
    public Object remove(@PathVariable("viId") Integer viId){
        vipIndentService.remove(viId);
        return new ResponseEntity(200,"商品删除成功",null);
    }
}
