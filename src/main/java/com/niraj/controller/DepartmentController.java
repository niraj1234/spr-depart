package com.niraj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niraj.entity.Department;
import com.niraj.service.DepartmentService;

@RestController
@RequestMapping("/d")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;
	
	@PostMapping("/department")
	public Department saveDepartment(@RequestBody Department department) {
		return departmentService.saveDepartment(department);	
	}

	@GetMapping("/department/all")
	public List<Department> getAllDepartments(){
		System.out.println("|||==>  Getting All data after AWS-CodePipeline ==> ");
		return departmentService.getAllDepartment();
	}

	@GetMapping("/department/{deptId}")
	public Department getDepartmentById(@PathVariable("deptId") Long deptId ) {
		return departmentService.getDepartmentById(deptId);
	}
	
}