package com.niraj.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long departmentId;
	
//	@Length(min = 5 , max = 20 , message = "Length should be between 5 and 20 ")
	@NotBlank(message = "Department name must not be blank ")
	@NotNull(message = "Department not Null")
	private String departmentName;

	
	private String departmentAddress;
	private String departmentCode;
	
}
