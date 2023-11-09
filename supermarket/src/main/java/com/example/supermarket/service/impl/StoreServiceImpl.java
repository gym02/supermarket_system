package com.example.supermarket.service.impl;

import com.example.supermarket.entity.PageBean;
import com.example.supermarket.entity.User;
import com.example.supermarket.entity.WareHouse;
import com.example.supermarket.entity.dto.StoreDTO;
import com.example.supermarket.entity.vo.StoreVO;
import com.example.supermarket.entity.vo.StoresVO;
import com.example.supermarket.exception.UserNotExistException;
import com.example.supermarket.mapper.StoreMapper;
import com.example.supermarket.service.StoreService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2023-10-13
 */
@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    StoreMapper storeMapper;
    @Override
    public void updateStore(StoreVO storeVO) {
        int i = storeMapper.updateStore(storeVO);
        if(i==0){
            storeMapper.insertStore(storeVO);
        }

    }

    @Override
    public void getUserByPwd(String userId, String userPwd) {
        String password = DigestUtils.md5DigestAsHex(userPwd.getBytes());
        User user=storeMapper.getUserByPwd(userId,password);
        if(user==null){
            throw new UserNotExistException("密码错误，请重新输入");
        }
    }

    @Override
    public PageBean<StoresVO> getStore(StoreDTO storeDTO) {
        PageHelper.startPage(storeDTO.getPageNum(),storeDTO.getPageSize());
        List<StoreVO> stores = storeMapper.getStore(storeDTO);
        List<StoresVO> storesVOS=new ArrayList<>();
        for(StoreVO s:stores){
            WareHouse wareHouserById = getWareHouserById(s.getWareHouse().getWhPid());
            StoresVO storesVO = new StoresVO();
            storesVO.setCargo(s.getCargo());
            storesVO.setCgUnit(s.getCgUnit());
            storesVO.setCgCount(s.getCgCount());
            storesVO.setWareHouse(s.getWareHouse());
            storesVO.setPwareHouse(wareHouserById);
            storesVOS.add(storesVO);
        }
        PageInfo<StoreVO> pageInfo=new PageInfo<>(stores);

        PageBean<StoresVO> pageBean=new PageBean<>(storeDTO.getPageNum(),storeDTO.getPageSize(),(int)pageInfo.getTotal(),
                pageInfo.getPages(),storesVOS);


        return pageBean;
    }

    @Override
    public WareHouse getWareHouserById(int whId) {

        return storeMapper.getWareHouseById(whId);
    }

    @Override
    public Object[] getMap(int whId) {
        List<Map<String,String>>maps=storeMapper.getMaps(whId);
        System.out.println(maps);
        List<String> name=new ArrayList<>();
        List<String>count=new ArrayList<>();
       Object[] Objects =new Object[2];
        for(Map<String,String> m:maps){

            name.add(m.get("name"));
           count.add(m.get("value"));

        }
        Objects[0]=name;
        Objects[1]=count;
        return Objects;
    }
}
