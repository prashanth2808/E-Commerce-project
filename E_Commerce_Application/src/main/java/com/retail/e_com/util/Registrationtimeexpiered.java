package com.retail.e_com.util;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Getter;

@RestController
@Getter
public class Registrationtimeexpiered extends RuntimeException {

	private String message;
	
	
}
