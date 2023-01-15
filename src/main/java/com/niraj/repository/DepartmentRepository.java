package com.niraj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.niraj.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department , Long>{

	public Department findByDepartmentNameIgnoreCase(String deptName);


	@Query(value = "select * from department where department_code = ?1 " , nativeQuery = true )
	public Department getDepartmentByCode(String deptCode);

}
