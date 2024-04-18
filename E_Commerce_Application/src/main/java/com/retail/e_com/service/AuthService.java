package com.retail.e_com.service;

import org.springframework.http.ResponseEntity;

import com.retail.e_com.entity.User;
import com.retail.e_com.request.Otprequest;
import com.retail.e_com.request.Userrequest;
import com.retail.e_com.response.Userresponse;
import com.retail.e_com.util.ResponseStructure;
import com.retail.e_com.util.SimpleResponseStructure;

public interface AuthService {

	public ResponseEntity<SimpleResponseStructure> userregistration(Userrequest userreq);

	public ResponseEntity<ResponseStructure<Userresponse>> verifyotp(Otprequest otp);

}
