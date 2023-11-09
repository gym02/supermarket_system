package com.example.supermarket.controller;


import com.example.supermarket.entity.ResponseResult;
import com.example.supermarket.entity.Supply;
import com.example.supermarket.entity.dto.SupplyDTO;
import com.example.supermarket.service.SupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2023-10-13
 */
@Controller
@ResponseBody
@RequestMapping("/supply")
public class SupplyController {
	@Autowired
	SupplyService supplyService;

	@PostMapping("/getAll")
	public ResponseResult<List<Supply>>getSupplies(@RequestBody SupplyDTO supplyDTO){
		List<Supply>supplies =supplyService.getByCondition(supplyDTO);
		return  new ResponseResult<List<Supply>>(200,"ok",supplies);
	}

	@PostMapping("/add")
	public ResponseResult<Void>addSupply( @Valid @RequestBody Supply supply){
		supplyService.addSupply(supply);
		return new ResponseResult<>(200,"ok",null);
	}
	@PostMapping("/edit")
	public ResponseResult<Void>editSupply(@Valid @RequestBody Supply supply){
		supplyService.editSupply(supply);
		return new ResponseResult<>(200,"ok",null);
	}
	@PostMapping("/deleteBySuId/{suId}")
	public  ResponseResult<Void>deleteBySuId(@PathVariable Integer suId){
		supplyService.deleteBySuId(suId);
		return new ResponseResult<>(200,"ok",null);
	}
	@PostMapping("/removeAll")
	public  ResponseResult<Void>removeAll(@RequestBody Supply supply){
		supplyService.removeAll(supply.getSuIds());
		return new ResponseResult<>(200,"ok",null);
	}
	@GetMapping("/getAllSupplies")
	public ResponseResult<List<Supply>>getAllSupplies(){
		List<Supply>supplies =supplyService.getAllSupplies();
		return new ResponseResult<List<Supply>>(200,"ok",supplies);
	}

}

