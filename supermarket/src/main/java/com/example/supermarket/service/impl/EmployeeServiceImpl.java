package com.example.supermarket.service.impl;

import com.example.supermarket.entity.PageBean;
import com.example.supermarket.entity.dto.EmployeeDTO;
import com.example.supermarket.entity.vo.EmployeeVO;
import com.example.supermarket.exception.PhoneExistsException;
import com.example.supermarket.mapper.EmployeeMapper;
import com.example.supermarket.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2023-10-13
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;
    @Override
    public PageBean<EmployeeVO> getEmployees(EmployeeDTO employeeDTO) {
        PageHelper.startPage(employeeDTO.getPageNum(),employeeDTO.getPageSize());
        List<EmployeeVO> employees = employeeMapper.getEmployees(employeeDTO);
        PageInfo<EmployeeVO> pageInfo=new PageInfo<>(employees);

        PageBean<EmployeeVO> pageBean=new PageBean<>(employeeDTO.getPageNum(),employeeDTO.getPageSize(),(int)pageInfo.getTotal(),
                pageInfo.getPages(),pageInfo.getList());


        return pageBean;



    }
    @Override
    public Boolean phoneExists(String empPhone) {
        return employeeMapper.getEmployeeByPhone(empPhone)!=null;
    }

    @Override
    public void addEmployee(EmployeeVO employeeVO) {
       if(phoneExists(employeeVO.getEmpPhone())){
            throw  new PhoneExistsException("电话被使用");
        }
        employeeMapper.addEmployee(employeeVO);

    }

    @Override
    public void delete(int empId) {
        employeeMapper.delete(empId);
    }

    @Override
    public void deleteEmployeesBatch(int[] eids) {
        employeeMapper.deleteEmployeesBatch(eids);
    }

    @Override
    public void update(EmployeeVO employeeVO) {
        employeeMapper.update(employeeVO);
    }
}
