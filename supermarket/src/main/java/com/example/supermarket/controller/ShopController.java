package com.example.supermarket.controller;


import com.example.supermarket.entity.ResponseResult;
import com.example.supermarket.entity.Shop;
import com.example.supermarket.entity.dto.ShopDTO;
import com.example.supermarket.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2023-10-13
 */
@Controller
@RequestMapping("/shop")

@Slf4j
public class ShopController {

	@Autowired
	ShopService shopService;

	@Value("${example.file-path}")
	String basePath;
	@PostMapping("/getAll")
	@ResponseBody
	public ResponseResult<List<Shop>>getAll(@RequestBody ShopDTO shopDTO){
	List<Shop>shops=shopService.getShopByCondition(shopDTO);
	return new ResponseResult<List<Shop>>(200,"ok",shops);
	}
	@PostMapping("/upload")
	@ResponseBody
	public  ResponseResult<String> uploadImage(MultipartFile file){
		//产生新的文件名
		String originalFilename = file.getOriginalFilename();
		String suffix=originalFilename.substring(originalFilename.lastIndexOf("."));
		String newFileName = UUID.randomUUID().toString().replaceAll("-", "");

		String pg=basePath+"/"+newFileName+suffix;
		log.info(pg);

		try {
			file.transferTo(new File(pg));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return new ResponseResult<>(200,"success",newFileName+suffix);
	}
	@PostMapping("/add")
	@ResponseBody
	public ResponseResult<Void>addShop(@Valid @RequestBody Shop shop){
		shopService.addShop(shop);
		return new ResponseResult<>(200,"ok",null);

	}
	@PostMapping("/edit")
	@ResponseBody
	public ResponseResult<Void>edit(@Valid @RequestBody Shop shop){
		shopService.editByMdid(shop);
		return new ResponseResult<>(200,"",null);
	}

	@PostMapping("/deleteByMdId/{mdId}")
	@ResponseBody
	public ResponseResult<Void>deleteShopByMdId(@PathVariable Integer mdId){
		shopService.deleteByMdId(mdId);
		return new ResponseResult<>(200,"",null);
	}
	@PostMapping("/removeAll")
	@ResponseBody
	public ResponseResult<Void>removeAll(@RequestBody Shop shop){
		shopService.deleteByMdIds(shop.getMdIds());
		return new ResponseResult<>(200,"ok",null);
	}
	@GetMapping("/export")
	public String export(@RequestParam(required = false) Shop shop, Map map){
		List<Shop>shops=shopService.getAllShop(shop);
		map.put("shops",shops);
		return "excelShopView";
	}

	@GetMapping("/getAllShops")
	@ResponseBody
	public ResponseResult<List<Shop>>getAllShops(){
		List<Shop>shops=shopService.getAllShops();
		return  new ResponseResult<List<Shop>>(200,"ok",shops);
	}
}

