package com.retail.e_com.request;

import com.retail.e_com.enums.UserRole;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Userrequest {

	@NotNull(message = "userName should not be null")

	private String name;
	 
	@NotBlank(message = "Email is required")
  //  @Email(message = "Email is not valid")
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "Email must end with @gmail.com")
    
	private String email;	

	
	@NotNull(message = "userPassword should not be null")
	@Size(min = 8, max = 16, message = "password should be length is 8>= &&<=16")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Password must"
			+ " contain at least one letter, one number, one special character")
	
	private String password;

	private UserRole role;																																																																																																																														
	
	
	
}
