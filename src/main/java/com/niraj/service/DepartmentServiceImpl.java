package com.niraj.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niraj.entity.Department;
import com.niraj.repository.DepartmentRepository;


@Service
public class DepartmentServiceImpl implements DepartmentService{

	
	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public Department saveDepartment(Department department) {
		return departmentRepository.save(department);
	}

	@Override
	public List<Department> getAllDepartment() {
		return departmentRepository.findAll();
	}

	@Override
	public Department getDepartmentById(Long deptId) {
		return departmentRepository.findById(deptId).get();
	}

	@Override
	public Department getDepartmentByName(String deptName) {
		return departmentRepository.findByDepartmentNameIgnoreCase(deptName);
	}

	@Override
	public Department getDepartmentByCode(String deptCode) {
		return departmentRepository.getDepartmentByCode(deptCode);
	}

	@Override
	public String deleteDepartmentById(Long deptId) {
		String dbResponse ="";
		try {
			departmentRepository.deleteById(deptId);			
			dbResponse ="SUCCESS";
		}catch(Exception ex) {
			dbResponse ="FAILED";
			ex.printStackTrace();
		}
		return dbResponse;
	}

	@Override
	public Department updateDepartment(Long deptId, Department department) {
		
		Department dept = departmentRepository.findById(deptId).get(); 
		
		if (Objects.nonNull(department.getDepartmentName()) 
				&& !"".equalsIgnoreCase(department.getDepartmentName())) {
			dept.setDepartmentName(department.getDepartmentName());
		}

		if (Objects.nonNull(department.getDepartmentAddress())
				&& !"".equalsIgnoreCase(department.getDepartmentAddress())) {
			dept.setDepartmentAddress(department.getDepartmentAddress());
		}

		if (Objects.nonNull(department.getDepartmentCode())
				&& !"".equalsIgnoreCase(department.getDepartmentCode())) {
			dept.setDepartmentCode(department.getDepartmentCode());
		}

		return departmentRepository.save(dept);
	}
	
	
	
	
}
