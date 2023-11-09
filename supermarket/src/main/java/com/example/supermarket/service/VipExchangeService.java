package com.example.supermarket.service;

import com.example.supermarket.entity.dto.VipExchangeDTO;
import com.example.supermarket.entity.dto.VipExchangeSearchDTO;

import java.util.List;

/**
 * @author 高垚淼
 * @version 1.0
 */
public interface VipExchangeService {
    void add(VipExchangeDTO vipExchangeDTO);

    List<VipExchangeSearchDTO> search(VipExchangeSearchDTO vipExchangeSearchDTO);

    void remove(Integer dhId);
}
