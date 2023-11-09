package com.example.supermarket.controller;


import com.example.supermarket.entity.PageBean;
import com.example.supermarket.entity.ResponseResult;
import com.example.supermarket.entity.dto.EmployeeDTO;
import com.example.supermarket.entity.vo.EmployeeVO;
import com.example.supermarket.mapper.EmployeeMapper;
import com.example.supermarket.service.EmployeeService;
import com.example.supermarket.validationgroup.EmployeeAddGroup;
import com.example.supermarket.validationgroup.EmployeeModifyGroup;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
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
@Slf4j
@RequestMapping("/employee")
public class EmployeeController {
      @Autowired
        EmployeeService employeeService;
      @Autowired
    EmployeeMapper employeeMapper;
      @ResponseBody
    @PostMapping("/get/all")
    public ResponseResult<PageBean<EmployeeVO>> getEmployee(@RequestBody EmployeeDTO employeeDTO){
        System.out.println(employeeDTO);

        return  new ResponseResult<PageBean<EmployeeVO>>(200,"succedss",employeeService.getEmployees(employeeDTO));
    }
    @ResponseBody
    @RequestMapping("/add")
    public ResponseResult<Void> addEmployee(@Validated(EmployeeAddGroup.class) @RequestBody EmployeeVO employeeVO/*, BindingResult bindingResult*/){
        //调用insert完成注册
        log.info("添加employeeVO={}",employeeVO);


         employeeService.addEmployee(employeeVO);
        return new ResponseResult<Void>(200,"success",null);
    }
    @ResponseBody
    @PostMapping("/delete/{empId}")
    public ResponseResult deleteEmployee(@PathVariable("empId") int empId){
        employeeService.delete(empId);
        return  new ResponseResult(200,"succedss",null);
    }
    @ResponseBody
    @PostMapping("/removebatch")
    public ResponseResult<Void> removeBatch(@RequestBody  EmployeeVO[] empId){

        int[]eids=new int[empId.length];
        for(int i=0;i<empId.length;i++){
            eids[i]=empId[i].getEmpId();
        }
        employeeService.deleteEmployeesBatch(eids);
        return  new ResponseResult(200,"succedss",null);
    }
    @ResponseBody
    @RequestMapping("/modify")
    public ResponseResult modifyEmployee(@Validated(EmployeeModifyGroup.class) @RequestBody EmployeeVO employeeVO /*, BindingResult bindingResult*/){
        log.info("修改employee={}",employeeVO);
        employeeService.update(employeeVO);
        return new ResponseResult<Void>(200,"success",null);
    }
    @GetMapping("/export")
    public String export(EmployeeDTO employeeDTO, Map map){

        //分页
        PageHelper.startPage(employeeDTO.getPageNum(),employeeDTO.getPageSize());
        //查询
        List<EmployeeVO> emps = employeeMapper.getEmployees(employeeDTO);
        map.put("emps",emps);
        //获取分页信息
        return "excelView";
    }
}

