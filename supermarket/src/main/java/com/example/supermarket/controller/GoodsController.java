package com.example.supermarket.controller;

import com.example.supermarket.entity.*;
import com.example.supermarket.entity.dto.GoodsDTO;
import com.example.supermarket.entity.vo.GoodsVO;
import com.example.supermarket.exception.TimeOutException;
import com.example.supermarket.service.GoodsService;
import com.example.supermarket.service.UserService;
import com.example.supermarket.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController 
@Slf4j
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    GoodsService goodsService;
    @Autowired
    UserService userService;


    @PostMapping("/get/all")
    public ResponseResult<PageBean<GoodsVO>> getGoods(@RequestBody GoodsDTO goodsDTO){
        System.out.println(goodsDTO);

        return  new ResponseResult<PageBean<GoodsVO>>(200,"succedss",goodsService.getGoods(goodsDTO));
    }
    @PostMapping("/cargo")
    public ResponseResult<List<Cargo>> getCargos(){


        return  new ResponseResult<List<Cargo>>(200,"succedss",goodsService.getCargos());
    }
    @RequestMapping("/add")
    public ResponseResult<Void> addGoods( @RequestBody GoodsVO goodsVO/*, BindingResult bindingResult*/){
        //调用insert完成注册
        log.info("添加employeeVO={}",goodsVO);


        goodsService.addGoods(goodsVO);
        return new ResponseResult<Void>(200,"success",null);
    }

    @RequestMapping("/user")
    public ResponseResult<User> getUser(HttpServletRequest request){
        //调用insert完成注册
        String token = request.getHeader("token");
        int userId= Integer.parseInt(JwtUtil.getUserId(token));

          User user= userService.getUserById(userId);

        return new ResponseResult<User>(200,"success",user);
    }
    @PostMapping("/delete/{storeapplyId}")
    public ResponseResult deleteEmployee(@PathVariable("storeapplyId") int storeapplyId){
        goodsService.delete(storeapplyId);
        return  new ResponseResult(200,"succedss",null);
    }
    @RequestMapping("/modify")
    public ResponseResult modifyEmployee( @RequestBody GoodsVO goodsVO /*, BindingResult bindingResult*/){
        log.info("修改employee={}",goodsVO);
        goodsService.update(goodsVO);
        return new ResponseResult<Void>(200,"success",null);
    }
    @PostMapping("/removebatch")
    public ResponseResult<Void> removeBatch(@RequestBody  GoodsVO[] empId){

        int[]eids=new int[empId.length];
        for(int i=0;i<empId.length;i++){
            eids[i]=empId[i].getStoreapplyId();
        }
        goodsService.deleteGoodsBatch(eids);
        return  new ResponseResult(200,"succedss",null);
    }
    @PostMapping("/id/{storeapplyId}")
    public ResponseResult<GoodsVO> getEmployByEid(@PathVariable("storeapplyId") int storeapplyId){

        GoodsVO goodsVO=goodsService.getGoodsById(storeapplyId);
        return  new ResponseResult(200,"succedss",goodsVO);
    }
    @RequestMapping("/get/allwh")
    public ResponseResult<List<WareHouse>> getAllMenus(){
        List<WareHouse> wareHouses= goodsService.getAllWarehouse();

        return  new ResponseResult<List<WareHouse>>(200,"success",wareHouses);
    }
    @GetMapping ("/time")
    public ResponseResult<String> time( String goodsfromDate){
        String replace = goodsfromDate.replace("-", "");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        int i = Integer.parseInt(replace);
        int j = Integer.parseInt(sdf.format(new Date()));
        if(i>=j){
            return new ResponseResult<>(200,"success","日期正确");}
        else{
           throw new TimeOutException("不能输入过期时间");}

    }
}
