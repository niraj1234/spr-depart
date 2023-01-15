package com.niraj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping("/departmentByName/{deptName}")
	public Department getDepartmentByName(@PathVariable("deptName") String deptName ) {
		return departmentService.getDepartmentByName(deptName);
	}

	@GetMapping("/departmentByCode/{deptCode}")
	public Department getDepartmentByCode(@PathVariable("deptCode") String deptCode) {
		return departmentService.getDepartmentByCode(deptCode);
	}

	@PostMapping("/department/many")
	public String saveManyDepartment(@RequestBody List<Department> departments) {
		System.out.println("Incoming Data" + departments);	
        departments.stream().forEach(d -> {
        	departmentService.saveDepartment(d);
        });		
		return "Records inserted ==> "+departments.size();	
	}


	@DeleteMapping("/department/{deptId}")
	public String deleteDEpartmentById(@PathVariable("deptId") Long deptId) {
		String dbResponse = departmentService.deleteDepartmentById(deptId);
		System.out.println("Department Data ==>  " + dbResponse);
		return dbResponse;		
	}
	

	@PutMapping("/department/update/{deptId}")
	public Department updateDepartment( @RequestBody Department department , 
			@PathVariable("deptId") Long deptId ) {
		Department updatedDepartment = departmentService.updateDepartment(deptId , department);		
		return updatedDepartment;
	}
	
}





