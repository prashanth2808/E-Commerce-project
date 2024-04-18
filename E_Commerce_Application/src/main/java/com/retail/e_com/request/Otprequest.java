package com.retail.e_com.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Otprequest {


	@NotBlank(message = "Email is required")
	//  @Email(message = "Email is not valid")
	@Email(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "Email must end with @gmail.com")
    private String email;
	
	@NotNull
	private String otp; 

}
