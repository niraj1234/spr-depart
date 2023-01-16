package com.niraj.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niraj.entity.Department;
import com.niraj.error.DepartmentNameParameterException;
import com.niraj.error.DepartmentNotFountException;
import com.niraj.repository.DepartmentRepository;


@Service
public class DepartmentServiceImpl implements DepartmentService{

	
	@Autowired
	private DepartmentRepository deptRepo;

	@Override
	public Department saveDepartment(Department department) {
		return deptRepo.save(department);
	}

	@Override
	public List<Department> getAllDepartment() {
		return deptRepo.findAll();
	}

	@Override
	public Department getDepartmentById(Long deptId) throws DepartmentNotFountException {

		Optional<Department> department = deptRepo.findById(deptId);
		if(!department.isPresent()) {
			throw new DepartmentNotFountException("Requested department not found id => "+ deptId);
		}
		return department.get();
	}

	@Override
	public Department getDepartmentByName(String deptName) throws DepartmentNameParameterException {

		Department dept = null;
		boolean deptNameFlag = false;
		if(deptName.equalsIgnoreCase("Electrical") || deptName.equalsIgnoreCase("Mechanical") ) {
			deptNameFlag = true;
		}
		
		if(!deptNameFlag) {
			throw new DepartmentNameParameterException("Department Name is not in valid RANGE");
		}else {
			dept = deptRepo.findByDepartmentNameIgnoreCase(deptName);
		}		
		return dept;
	}

	@Override
	public Department getDepartmentByCode(String deptCode) {
		return deptRepo.getDepartmentByCode(deptCode);
	}

	@Override
	public String deleteDepartmentById(Long deptId) {
		String dbResponse ="";
		try {
			deptRepo.deleteById(deptId);			
			dbResponse ="SUCCESS";
		}catch(Exception ex) {
			dbResponse ="FAILED";
			ex.printStackTrace();
		}
		return dbResponse;
	}

	@Override
	public Department updateDepartment(Long deptId, Department department) {
		
		Department dept = deptRepo.findById(deptId).get(); 
		
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

		return deptRepo.save(dept);
	}
	
	
	
	
}
