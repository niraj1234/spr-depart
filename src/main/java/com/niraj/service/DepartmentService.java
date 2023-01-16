package com.niraj.service;

import java.util.List;

import com.niraj.entity.Department;
import com.niraj.error.DepartmentNameParameterException;
import com.niraj.error.DepartmentNotFountException;

public interface DepartmentService {

	Department saveDepartment(Department department);
	List<Department> getAllDepartment();
	Department getDepartmentById(Long deptId) throws DepartmentNotFountException;
	Department getDepartmentByName(String deptName) throws DepartmentNameParameterException;
	Department getDepartmentByCode(String deptCode);
	String deleteDepartmentById(Long deptId);
	Department updateDepartment(Long deptId, Department department);

}
