package com.example.supermarket.controller;


import com.example.supermarket.entity.Kind;
import com.example.supermarket.entity.ResponseResult;
import com.example.supermarket.entity.dto.KindDTO;
import com.example.supermarket.service.KindService;
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
@RequestMapping("/kind")
public class KindController {

	@Autowired
	KindService kindService;

	@PostMapping("/getAll")
	public ResponseResult<List<Kind>>getAllKinds(@RequestBody KindDTO kindDTO){
		List<Kind>kinds =kindService.getByCondition(kindDTO);
		return new ResponseResult<List<Kind>>(200,"ok",kinds);
	}
	@PostMapping("/add")
	public ResponseResult<Void>addkind(@RequestBody Kind kind){
		kindService.addkind(kind);
		return new ResponseResult<>(200,"ok",null);
	}
	@PostMapping("/edit")
	public ResponseResult<Void>editkind(@Valid @RequestBody Kind kind){
		kindService.editkind(kind);
		return new ResponseResult<>(200,"ok",null);
	}
	@PostMapping("/deleteByZlId/{zlId}")
	public  ResponseResult<Void>deleteByZlId(@PathVariable Integer zlId){
		kindService.deleteByZlId(zlId);
		return new ResponseResult<>(200,"ok",null);
	}
	@PostMapping("/removeAll")
	public  ResponseResult<Void>removeAll(@RequestBody Kind kind){
		kindService.removeAll(kind.getZlIds());
		return new ResponseResult<>(200,"ok",null);
	}
	@GetMapping("/getAllKinds")
	public ResponseResult<List<Kind>>getAllKinds(){
		List<Kind>kinds=kindService.getAllKinds();
		return new ResponseResult<List<Kind>>(200,"ok",kinds);
	}
}

