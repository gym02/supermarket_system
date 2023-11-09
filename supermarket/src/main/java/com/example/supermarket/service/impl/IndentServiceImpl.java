package com.example.supermarket.service.impl;

import com.example.supermarket.entity.Kind;
import com.example.supermarket.entity.PageBean;
import com.example.supermarket.entity.Product;
import com.example.supermarket.entity.SendWay;
import com.example.supermarket.entity.dto.FlowDTO;
import com.example.supermarket.entity.dto.IndentDTO;
import com.example.supermarket.entity.dto.OrderDTO;
import com.example.supermarket.entity.vo.IndentVO;
import com.example.supermarket.mapper.IndentMapper;
import com.example.supermarket.service.IndentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndentServiceImpl implements IndentService {
    @Autowired
    IndentMapper indentMapper;

    @Override
    public PageBean<IndentVO> getAllIndent(int pageIndex, int pageSize, IndentDTO indentDTO) {
        PageHelper.startPage(pageIndex,pageSize);
        List<IndentVO> list = indentMapper.getAllIndent(indentDTO);
        //将查到的所有信息封装成一个完整的分页信息
        PageInfo<IndentVO> pageInfo = new PageInfo<>(list);
        //将所有信息封装成对象并发给前端
        PageBean<IndentVO> pageBean = new PageBean<>(pageIndex, pageSize,
                (int)pageInfo.getTotal(), pageInfo.getPages(), pageInfo.getList());
        return pageBean;
    }

    @Override
    public List<SendWay> getAllSendWay() {
        return indentMapper.getAllSendWay();
    }

    @Override
    public void changeDdStatus(String ddId, String type) {
        indentMapper.changeDdStatus(ddId, type);
    }

    @Override
    public void addNewOrder(OrderDTO orderDTO) {
        indentMapper.addNewOrder(orderDTO);
    }

    @Override
    public List<Kind> getAllKind() {
        return indentMapper.getAllKind();
    }

    @Override
    public void deleteByDdId(String ddId) {
        indentMapper.deleteByDdId(ddId);
    }

    @Override
    public boolean removeIndentBatch(String[] ids) {
        for (String ddId : ids){
            if(!"已完成".equals(indentMapper.selectDdTypeByDdId(ddId))){
                return false;
            }
        }
        for (String ddId : ids){
            indentMapper.deleteByDdId(ddId);
        }
        return true;
    }

    @Override
    public void finishIndentByDdId(String ddId) {
        indentMapper.finishIndentByDdId(ddId);
    }

    @Override
    public int getCustomerIdByDdId(String orderId) {
        return indentMapper.getCustomerIdByDdId(orderId);
    }

    @Override
    public void modifyFlowInfo(FlowDTO flowDTO) {
        indentMapper.modifyFlowInfo(flowDTO);
    }

    @Override
    public void modifyFlowInfo1(FlowDTO flowDTO) {
        indentMapper.modifyFlowInfo1(flowDTO);
    }

    @Override
    public List<Product> getAllProductNameByZlId(Integer zlId) {
        return indentMapper.getAllProductNameByZlId(zlId);
    }

    @Override
    public double getPriceByProductName(Integer spId) {
        return indentMapper.getPriceByProductName(spId);
    }

    @Override
    public boolean selectCustomer(String name, String phone) {
        Integer id = indentMapper.selectCustomer(name,phone);
        System.out.println(id);
        if(id==null){
            return false;
        }
        return true;
    }

    @Override
    public Integer addNewCustomer(OrderDTO orderDTO) {
        indentMapper.addNewCustomer(orderDTO);
        return indentMapper.getLastInsertId();
    }

    @Override
    public void insertNewGkId(String ddId, int gkId) {
        indentMapper.insertNewGkId(ddId, gkId);
    }

    @Override
    public void addNewFhDate(String ddId, String time) {
        indentMapper.addNewFhDate(ddId, time);
    }

    @Override
    public void addNewFkDate(String ddId, String time) {
        indentMapper.addNewFkDate(ddId, time);
    }

    @Override
    public void addNewShDate(String ddId, String time) {
        indentMapper.addNewShDate(ddId, time);
    }

    @Override
    public void addNewWcDate(String ddId, String time) {
        indentMapper.addNewWcDate(ddId, time);
    }

    @Override
    public void addNewAfterSale(String str, String ddId, String time) {
        indentMapper.addNewAfterSale(str, ddId, time);
    }

}
