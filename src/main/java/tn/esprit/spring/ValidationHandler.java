package tn.esprit.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import tn.esprit.spring.entity.ApiError;

@ControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler{
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,HttpHeaders headers, HttpStatus status, WebRequest request) {
			
			//Creating Mapped variable
			Map<String, String> errors = new HashMap<>();
			
			//Binding raisedException to message
			ex.getBindingResult().getAllErrors().forEach((error) ->{
								
				//Retrieving fieldName
				String fieldName = ((FieldError) error).getField();
				
				//Retrieving error
				String message = error.getDefaultMessage();
				
				//Updating our Mapped variable with our custom output
				errors.put(fieldName, message);
																	});
			
		return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
	protected ResponseEntity<Object>handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request){

//		//Creating Mapped variable
//		Map<String, String> errors = new HashMap<>();
//		
//		
//							
//			//Retrieving fieldName
//			String fieldName = ex.getPropertyName() + " parameter is missing";
//			
//			//Retrieving error
//			String message = ex.getLocalizedMessage();
//			
//			//Updating our Mapped variable with our custom output
//			errors.put(fieldName, message);

			    String error =  ex.getName() + " should be of type " + ex.getRequiredType().getName();

			    ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
			    
			    return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
																
	}
	
	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<Object> handleConstraintViolation(
	  ConstraintViolationException ex, WebRequest request) {
	    List<String> errors = new ArrayList<String>();
	    for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
	        errors.add(violation.getRootBeanClass().getName() + " " + 
	          violation.getPropertyPath() + ": " + violation.getMessage());
	    }

	    ApiError apiError =  new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
	    return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}
	
	/*
	@ExceptionHandler({ HttpMessageNotReadableException.class })
	public ResponseEntity<Object> handleHttpMessageNotReadableException(
			HttpMessageNotReadableException ex, WebRequest request) {
		

	}
	*/
	
	
	
}
