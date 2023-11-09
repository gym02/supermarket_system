package com.example.supermarket.controller;


import com.example.supermarket.entity.Cargo;
import com.example.supermarket.entity.PageBean;
import com.example.supermarket.entity.ResponseResult;
import com.example.supermarket.entity.WareHouse;
import com.example.supermarket.entity.dto.StoreDTO;
import com.example.supermarket.entity.vo.StoreVO;
import com.example.supermarket.entity.vo.StoresVO;
import com.example.supermarket.mapper.GoodsMapper;
import com.example.supermarket.mapper.StoreMapper;
import com.example.supermarket.service.StoreService;
import com.example.supermarket.util.JwtUtil;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2023-10-13
 */
@Controller
@Slf4j
@RequestMapping("/store")
public class StoreController {
    @Autowired
    StoreService storeService;
    @Autowired
    StoreMapper storeMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @ResponseBody
    @RequestMapping("/update")
    public ResponseResult<Void> updateStore( @RequestBody StoreVO storeVO/*, BindingResult bindingResult*/){
        //调用insert完成注册
        log.info("添加employeeVO={}",storeVO);


      storeService.updateStore(storeVO);

        return new ResponseResult<Void>(200,"success",null);
    }
    @ResponseBody
    @PostMapping("/user/{userPwd}")
    public ResponseResult<Void> getEmployByEid(@PathVariable("userPwd") String userPwd,HttpServletRequest request){
        String refresh = request.getHeader("refresh");
        String userId = JwtUtil.getUserId(refresh);
        storeService.getUserByPwd(userId,userPwd);
        return  new ResponseResult(200,"succedss",null);
    }
    @ResponseBody
    @PostMapping("/get/all")
    public ResponseResult<PageBean<StoresVO>> getStore(@RequestBody StoreDTO storeDTO){
        System.out.println(storeDTO);

        return  new ResponseResult<PageBean<StoresVO>>(200,"succedss",storeService.getStore(storeDTO));
    }
    @ResponseBody
    @PostMapping("/getbyid/{whId}")
    public ResponseResult getWareHouseById(@PathVariable("whId") int whId){
      WareHouse wareHouse= storeService.getWareHouserById(whId);
        return  new ResponseResult(200,"succedss",wareHouse);
    }

    @GetMapping("/export")
    public String export(StoreDTO storeDTO, Map map){

        //分页
        PageHelper.startPage(storeDTO.getPageNum(),storeDTO.getPageSize());
        //查询
        PageBean<StoresVO> store = storeService.getStore(storeDTO);

        map.put("stores",store.getBeanList());
        //获取分页信息
        return "storeExcelView";
    }
    @ResponseBody
    @PostMapping("/map/{whId}")
    public ResponseResult<Object> getMap(@PathVariable("whId") int whId){
       Object[]objects= storeService.getMap(whId);
        return  new ResponseResult(200,"succedss",objects);
    }
    @ResponseBody
    @PostMapping("/count")
    public ResponseResult<Object> getCount(@RequestBody StoreDTO storeDTO){
        List<StoreVO> store = storeMapper.getStore(storeDTO);
        List<Cargo> cargos = goodsMapper.getCargos();

       List<Map<String,String>> maps=new ArrayList<>();

       for(Cargo c:cargos){
           Map<String,String> map=new HashMap<>();
           int number=0;
           for (StoreVO s:store){
               if(c.getCargoId()==s.getCargo().getCargoId()){
                   number=number+s.getCgCount();
               }
           }
           map.put("name",c.getCargoName());
           map.put("value",String.valueOf(number));
           maps.add(map);
       }
        return  new ResponseResult(200,"succedss",maps);
    }

}

