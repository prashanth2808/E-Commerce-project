package com.retail.e_com.response;

import com.retail.e_com.enums.UserRole;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter


public class Userresponse {

	private int userId;

	private String name;

	private String email;

	private UserRole role;	
	
	private boolean isEmailVerified;
	
	private boolean isDeleted;
	
}
