package com.example.supermarket.service;

import com.example.supermarket.entity.PageBean;
import com.example.supermarket.entity.dto.AfterSaleDTO;
import com.example.supermarket.entity.dto.AfterSaleTypeDTO;
import com.example.supermarket.entity.vo.AfterSaleVO;

public interface AfterSaleService {
    PageBean<AfterSaleVO> getAllAfterSale(int pageIndex, int pageSize, AfterSaleDTO afterSaleDTO);

    void agree(String ddId, String asStatus, String timeNow);

    void changeAfterSaleType(AfterSaleTypeDTO afterSaleTypeDTO);

    void changeIndentDdStatus(String ddId);

    void finAfterSale(String ddId, String timeNow);

    void dealRequest1(String ddId, String asStatus, String timeNow);

    void dealRequest2(String ddId, String asStatus, String timeNow);

    void reject(String ddId, String timeNow);
}
