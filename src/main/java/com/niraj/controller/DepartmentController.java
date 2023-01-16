package com.niraj.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niraj.entity.Department;
import com.niraj.error.DepartmentNameParameterException;
import com.niraj.error.DepartmentNotFountException;
import com.niraj.service.DepartmentService;

@RestController
@RequestMapping("/department")
public class DepartmentController {

	final Logger L = LoggerFactory.getLogger(DepartmentController.class);
	
	@Value("${admin.email}")
	private String adminEmail;
	
	@Autowired
	private DepartmentService deptService;
	
	@PostMapping("/")
	public Department saveDepartment(@Valid @RequestBody Department department) {
		L.info("Department Data for add => " + department);
		return deptService.saveDepartment(department);	
	}

	@GetMapping("/all")
	public List<Department> getAllDepartments(){
		System.out.println("|||==>  Getting All data after AWS-CodePipeline ==> "+ adminEmail);
		return deptService.getAllDepartment();
	}

	@GetMapping("/{deptId}")
	public Department getDepartmentById(@PathVariable("deptId") Long deptId) throws DepartmentNotFountException {
		return deptService.getDepartmentById(deptId);
	}
	
	@GetMapping("/byName/{deptName}")
	public Department getDepartmentByName(@PathVariable("deptName") String deptName) throws DepartmentNameParameterException {
		return deptService.getDepartmentByName(deptName);
	}

	@GetMapping("/byCode/{deptCode}")
	public Department getDepartmentByCode(@PathVariable("deptCode") String deptCode) {
		return deptService.getDepartmentByCode(deptCode);
	}

	@PostMapping("/many")
	public String saveManyDepartment(@RequestBody List<Department> departments) {
		System.out.println("Incoming Data" + departments);	
        departments.stream().forEach(d -> {
        	deptService.saveDepartment(d);
        });		
		return "Records inserted ==> "+departments.size();	
	}


	@DeleteMapping("/delete/{deptId}")
	public String deleteDEpartmentById(@PathVariable("deptId") Long deptId) {
		String dbResponse = deptService.deleteDepartmentById(deptId);
		System.out.println("Department Data ==>  " + dbResponse);
		return dbResponse;		
	}
	

	@PutMapping("/update/{deptId}")
	public Department updateDepartment( @RequestBody Department department, 
			@PathVariable("deptId") Long deptId ) {
		Department updatedDepartment = deptService.updateDepartment(deptId , department);		
		return updatedDepartment;
	}
	
}





