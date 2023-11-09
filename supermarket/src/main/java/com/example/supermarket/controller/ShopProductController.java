package com.example.supermarket.controller;

import com.example.supermarket.entity.PageBean;
import com.example.supermarket.entity.ResponseResult;
import com.example.supermarket.entity.ShopProduct;
import com.example.supermarket.entity.vo.ShopProductVO;
import com.example.supermarket.service.ShopProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shopProduct")
public class ShopProductController {
@Autowired
	ShopProductService shopProductService;

@PostMapping("/add")
	public ResponseResult<Void>add(@RequestBody ShopProduct shopProduct){
	shopProductService.add(shopProduct);
	return  new ResponseResult<>(200,"ok",null);
}

@PostMapping("/putAll")
	public ResponseResult<Void>puttAllProduct(@RequestBody ShopProduct shopProduct){
	shopProductService.puttAllProduct(shopProduct);
	return new ResponseResult<>(200,"ok",null);
}

@PostMapping("/getAll/{pageNum}/{pageSize}")
	public ResponseResult<PageBean<ShopProductVO>>getShopPrdouct(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize,@RequestBody ShopProduct shopProduct){
	return new  ResponseResult<PageBean<ShopProductVO>>(200,"ok",shopProductService.getAllShopProduct(pageNum,pageSize,shopProduct));
}

@PostMapping("/pullProductBySpId/{spId}/{mdId}")
	public ResponseResult<Void>pullProduct(@PathVariable("spId")Integer spId,@PathVariable("mdId")Integer mdId){
	shopProductService.pullProductBySpId(spId,mdId);
	return new ResponseResult<>(200,"ok",null);
}
	@PostMapping("/putProductBySpId/{spId}/{mdId}")
	public ResponseResult<Void>putProductBySpId(@PathVariable("spId")Integer spId,@PathVariable("mdId")Integer mdId){
		shopProductService.putProductBySpId(spId,mdId);
		return new ResponseResult<>(200,"ok",null);
	}

}
