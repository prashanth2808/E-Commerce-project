package com.retail.e_com.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.retail.e_com.util.ErrorStructure;
import com.retail.e_com.util.UserAllreadyPresentException;

import lombok.AllArgsConstructor;

@RestController
//@AllArgsConstructor
public class AuthcoustomExceptionHandler {
	
	private ErrorStructure<String> errorStructure;

	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handlingUserAllreadyPresentException(UserAllreadyPresentException e) {
		return ResponseEntity.badRequest().body(errorStructure.setStatuscode(HttpStatus.BAD_REQUEST.value())
				.setMessage("user is allready present please enter Unique UserName").setrootcause(e.getMessage()));
	}
//
//	@ExceptionHandler
//	public ResponseEntity<ErrorStructure<String>> handlingEmailAllreadyPresentException(
//			EmailAllreadyPresentException e) {
//		return ResponseEntity.badRequest().body(errorStructure.setStatuscode(HttpStatus.BAD_REQUEST.value())
//				.setMessage("Email is allready present please enter Unique UserName").setrootcause(e.getMessage()));
//	}
//
//	@ExceptionHandler // InvalidOTPException
//	public ResponseEntity<ErrorStructure<String>> handlingRoleNotSpecifyException(RoleNotSpecifyException e) {
//		return ResponseEntity.badRequest().body(errorStructure.setStatuscode(HttpStatus.BAD_REQUEST.value())
//				.setMessage("Role is Not present please enter Role To The User").setrootcause(e.getMessage()));
//	}
//
//	@ExceptionHandler // InvalidOTPException
//	public ResponseEntity<ErrorStructure<String>> handlingInvalidOTPException(InvalidOTPException e) {
//		return ResponseEntity.badRequest().body(errorStructure.setStatuscode(HttpStatus.BAD_REQUEST.value())
//				.setMessage("invalid otp ..please enter correct otp....").setrootcause(e.getMessage()));
//	}
//
//	@ExceptionHandler // InvalidOTPException
//	public ResponseEntity<ErrorStructure<String>> handlingOtpExpaireException(OtpExpaireException e) {
//		return ResponseEntity.badRequest().body(errorStructure.setStatuscode(HttpStatus.BAD_REQUEST.value())
//				.setMessage("invalid otp ..Otp Is  eExpaired....").setrootcause(e.getMessage()));
//	}
//
//	@ExceptionHandler // InvalidOTPException
//	public ResponseEntity<ErrorStructure<String>> handlingRegistrationSessionExpaireException(
//			RegistrationSessionExpaireException e) {
//		return ResponseEntity.badRequest().body(errorStructure.setStatuscode(HttpStatus.BAD_REQUEST.value())
//				.setMessage("User registration Time Expaired...Pleace Try Again......").setrootcause(e.getMessage()));
	}


