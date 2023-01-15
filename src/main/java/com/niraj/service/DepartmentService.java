package com.niraj.service;

import java.util.List;

import com.niraj.entity.Department;

public interface DepartmentService {

	Department saveDepartment(Department department);

	List<Department> getAllDepartment();

	Department getDepartmentById(Long deptId);

	Department getDepartmentByName(String deptName);

	Department getDepartmentByCode(String deptCode);

	String deleteDepartmentById(Long deptId);

	Department updateDepartment(Long deptId, Department department);

}
