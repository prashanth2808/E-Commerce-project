package com.retail.e_com.util;

import javax.xml.crypto.Data;

import org.springframework.stereotype.Component;

import lombok.Getter;


@Getter
@Component
public class ErrorStructure<T> {

	private T rootcause;
	private int Statuscode;
	private String message;
	
	
	public ErrorStructure<T> setrootcause(T rootcause) {
		rootcause=rootcause;
	return this;
	}
	public ErrorStructure<T> setStatuscode(int statuscode) {
		this.Statuscode = Statuscode ;
	return this;
	}
	public ErrorStructure<T> setMessage(String message) {
		this.message = message;
	return this;
	}

}
