package com.retail.e_com.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.retail.e_com.request.Otprequest;
import com.retail.e_com.request.Userrequest;
import com.retail.e_com.response.Userresponse;
import com.retail.e_com.service.AuthService;
import com.retail.e_com.service.AuthServiceimpl;
import com.retail.e_com.util.ResponseStructure;
import com.retail.e_com.util.SimpleResponseStructure;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
//@RequestMapping ("/api/v1")
public class AuthController {

	private AuthServiceimpl service;

	@PostMapping("/user/register")
	public ResponseEntity<SimpleResponseStructure> userRegistration(@Valid @RequestBody Userrequest userrequest) {
		return service.userregistration(userrequest);
	}

	@PostMapping("/user/verifyemail")
	public ResponseEntity<ResponseStructure<Userresponse>> verifyotp(@RequestBody Otprequest otpreq) {
		return service.verifyotp(otpreq);
	}
}
