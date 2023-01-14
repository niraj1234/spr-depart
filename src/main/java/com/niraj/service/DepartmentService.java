package com.niraj.service;

import java.util.List;

import com.niraj.entity.Department;

public interface DepartmentService {

	Department saveDepartment(Department department);

	List<Department> getAllDepartment();

}
