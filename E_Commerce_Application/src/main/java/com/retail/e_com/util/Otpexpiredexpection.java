package com.retail.e_com.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Otpexpiredexpection extends RuntimeException {

	
	private String message;
}
