package com.niraj.error;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler({DepartmentNotFountException.class})
	public ResponseEntity<ErrorMessage> departmentNotFoundException(DepartmentNotFountException ex, WebRequest request) {
		ErrorMessage message = ErrorMessage.builder()
				.message("Department Not Found with given id")
				.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
	}	
		
	
    @ExceptionHandler({ConstraintViolationException.class})
	public ResponseEntity<ErrorMessage> departmentConstraintMissingException(ConstraintViolationException ex , WebRequest request){    	
	 List<String> errors = new ArrayList<String>();
	    for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
	        errors.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": " + violation.getMessage());
	    }
		    
	 ErrorMessage message = ErrorMessage.builder()
				.message("Department Name is Missing is POST JSON Data ==> "+ errors.toString())
				.status(HttpStatus.BAD_REQUEST).build();						
    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);		
	}
	

    @ExceptionHandler({DepartmentNameParameterException.class})
    public ResponseEntity<ErrorMessage> departmentNamePredefinedException(DepartmentNameParameterException ex, WebRequest request){
    	ErrorMessage message = ErrorMessage.builder()
    			.message("Permitted Department Name Not Matched")
    			.status(HttpStatus.PRECONDITION_FAILED).build();
    	return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(message);
    	
    }
    
    
    
}



