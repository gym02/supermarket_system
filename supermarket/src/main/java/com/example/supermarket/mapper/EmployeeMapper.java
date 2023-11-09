package com.example.supermarket.mapper;

import com.example.supermarket.entity.dto.EmployeeDTO;
import com.example.supermarket.entity.vo.EmployeeVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2023-10-13
 */
@Mapper
public interface EmployeeMapper {

    List<EmployeeVO> getEmployees(EmployeeDTO employeeDTO);
    @Select("select * from employee where emp_phone=#{empPhone}")
    EmployeeVO getEmployeeByPhone(String empPhone);
    @Insert("insert into employee values (null,#{empName},#{empAge},#{empGender},#{empPhone},#{empDate},1,#{file.id},0,#{shop.mdId}) ")
    void addEmployee(EmployeeVO employeeVO);
    @Update("update employee set isdelete = 1 where emp_id = #{empId}")
    void delete(int empId);

    void deleteEmployeesBatch(@Param("eids")int[] eids);
    @Update("update employee set emp_name=#{empName},emp_age=#{empAge},emp_gender=#{empGender},emp_phone=#{empPhone},emp_date=#{empDate},md_id=#{shop.mdId} where emp_id=#{empId}")
    void update(EmployeeVO employeeVO);
}
