package com.example.supermarket.controller;


import com.example.supermarket.entity.PageBean;
import com.example.supermarket.entity.Product;
import com.example.supermarket.entity.ResponseResult;
import com.example.supermarket.entity.dto.ProductDTO;
import com.example.supermarket.entity.vo.ProductVO;
import com.example.supermarket.service.ProductService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

@RequestMapping("/product")
public class ProductController {
	@Autowired
	ProductService productService;

	@PostMapping("/getAll/{pageNum}/{pageSize}")
	@ResponseBody
	public ResponseResult<PageBean<ProductVO>>getProduct(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize, @RequestBody ProductDTO productDTO){
		return new ResponseResult<PageBean<ProductVO>>(200,"ok",productService.getByCondition(pageNum,pageSize,productDTO));
	}

	@PostMapping("/add")
	@ResponseBody
	public ResponseResult<Void>addProduct(@RequestBody Product product){
		productService.addProduct(product);
		return new ResponseResult<>(200,"ok",null);
	}
	@PostMapping("/edit")
	@ResponseBody
	public ResponseResult<Void>editProduct(@RequestBody Product product){
		productService.editProduct(product);
		return new ResponseResult<>(200,"ok",null);
	}
	@PostMapping("/deleteBySpId/{spId}")
	@ResponseBody
	public ResponseResult<Void>deleteBySpId(@PathVariable Integer spId){
		productService.deleteBySpId(spId);
		return new ResponseResult<>(200,"ok",null);
	}
	@PostMapping("/removeAll")
	@ResponseBody
	public ResponseResult<Void>removeAll(@RequestBody Product product){
		productService.removeAll(product.getSpIds());
		return new ResponseResult<>(200,"ok",null);
	}
	@GetMapping("/export")
	public String export(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize")  Integer pageSize, @RequestParam(required = false) ProductVO productVO, Map map){
		System.out.println(productVO);
		PageHelper.startPage(pageNum,pageSize);
		List<ProductVO> products=productService.getAllProduct(productVO);
		map.put("products",products);
		return "excelProductView";
	}
}

