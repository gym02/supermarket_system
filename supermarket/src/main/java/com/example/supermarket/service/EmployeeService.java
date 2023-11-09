package com.example.supermarket.service;

import com.example.supermarket.entity.PageBean;
import com.example.supermarket.entity.dto.EmployeeDTO;
import com.example.supermarket.entity.vo.EmployeeVO;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2023-10-13
 */
public interface EmployeeService {

    PageBean<EmployeeVO> getEmployees(EmployeeDTO employeeDTO);

    void addEmployee(EmployeeVO employeeVO);
    Boolean phoneExists(String empPhone);

    void delete(int empId);

    void deleteEmployeesBatch(int[] eids);

    void update(EmployeeVO employeeVO);
}
