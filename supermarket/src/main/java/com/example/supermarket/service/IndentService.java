package com.example.supermarket.service;

import com.example.supermarket.entity.Kind;
import com.example.supermarket.entity.PageBean;
import com.example.supermarket.entity.Product;
import com.example.supermarket.entity.SendWay;
import com.example.supermarket.entity.dto.FlowDTO;
import com.example.supermarket.entity.dto.IndentDTO;
import com.example.supermarket.entity.dto.OrderDTO;
import com.example.supermarket.entity.vo.IndentVO;

import java.util.List;

public interface IndentService{

    PageBean<IndentVO> getAllIndent(int pageIndex, int pageSize, IndentDTO indentDTO);

    List<SendWay> getAllSendWay();

    void changeDdStatus(String ddId, String type);

    void addNewOrder(OrderDTO orderDTO);

    List<Kind> getAllKind();

    void deleteByDdId(String ddId);

    boolean removeIndentBatch(String[] ids);

    void finishIndentByDdId(String ddId);

    int getCustomerIdByDdId(String orderId);

    void modifyFlowInfo(FlowDTO flowDTO);

    void modifyFlowInfo1(FlowDTO flowDTO);

    List<Product> getAllProductNameByZlId(Integer zlId);

    double getPriceByProductName(Integer spId);

    boolean selectCustomer(String name, String phone);

    Integer addNewCustomer(OrderDTO orderDTO);

    void insertNewGkId(String ddId, int gkId);

    void addNewFhDate(String ddId, String time);

    void addNewFkDate(String ddId, String time);

    void addNewShDate(String ddId, String time);

    void addNewWcDate(String ddId, String time);

    void addNewAfterSale(String str, String ddId, String time);
}
