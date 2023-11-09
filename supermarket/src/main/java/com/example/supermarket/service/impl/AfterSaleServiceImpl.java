package com.example.supermarket.service.impl;

import com.example.supermarket.entity.PageBean;
import com.example.supermarket.entity.dto.AfterSaleDTO;
import com.example.supermarket.entity.dto.AfterSaleTypeDTO;
import com.example.supermarket.entity.vo.AfterSaleVO;
import com.example.supermarket.mapper.AfterSaleMapper;
import com.example.supermarket.service.AfterSaleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AfterSaleServiceImpl implements AfterSaleService {
    @Autowired
    AfterSaleMapper afterSaleMapper;

    @Override
    public PageBean<AfterSaleVO> getAllAfterSale(int pageIndex, int pageSize, AfterSaleDTO afterSaleDTO) {
        PageHelper.startPage(pageIndex,pageSize);
        List<AfterSaleVO> list = afterSaleMapper.getAllAfterSale(afterSaleDTO);
        //将查到的所有信息封装成一个完整的分页信息
        PageInfo<AfterSaleVO> pageInfo = new PageInfo<>(list);
        //将所有信息封装成对象并发给前端
        PageBean<AfterSaleVO> pageBean = new PageBean<>(pageIndex, pageSize,
                (int)pageInfo.getTotal(), pageInfo.getPages(), pageInfo.getList());
        return pageBean;
    }

    @Override
    public void agree(String ddId, String asStatus, String timeNow) {
        afterSaleMapper.agree(ddId, asStatus, timeNow);
    }

    @Override
    public void changeAfterSaleType(AfterSaleTypeDTO afterSaleTypeDTO) {
        afterSaleMapper.changeAfterSaleType(afterSaleTypeDTO);
    }

    @Override
    public void changeIndentDdStatus(String ddId) {
        afterSaleMapper.changeIndentDdStatus(ddId);
    }

    @Override
    public void finAfterSale(String ddId, String timeNow) {
        afterSaleMapper.finAfterSale(ddId, timeNow);
    }

    @Override
    public void dealRequest1(String ddId, String asStatus, String timeNow) {
        afterSaleMapper.dealRequest1(ddId, asStatus, timeNow);
    }

    @Override
    public void dealRequest2(String ddId, String asStatus, String timeNow) {
        afterSaleMapper.dealRequest2(ddId, asStatus, timeNow);
    }

    @Override
    public void reject(String ddId, String timeNow) {
        afterSaleMapper.reject(ddId, timeNow);
    }
}
