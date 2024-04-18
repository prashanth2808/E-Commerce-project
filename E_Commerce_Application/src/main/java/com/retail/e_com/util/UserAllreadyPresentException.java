package com.retail.e_com.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserAllreadyPresentException extends RuntimeException{

	private String message; 
}
