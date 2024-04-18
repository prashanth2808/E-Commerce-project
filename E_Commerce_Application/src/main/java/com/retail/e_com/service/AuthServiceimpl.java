package com.retail.e_com.service;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.Random;

import javax.management.relation.RoleInfoNotFoundException;
import javax.management.relation.RoleNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.retail.e_com.cache.CacheStorer;
import com.retail.e_com.entity.Customer;
import com.retail.e_com.entity.Seller;
import com.retail.e_com.entity.User;
import com.retail.e_com.enums.UserRole;
import com.retail.e_com.mailservice.MailService;
import com.retail.e_com.repository.UserRepository;
import com.retail.e_com.request.Otprequest;
import com.retail.e_com.request.Userrequest;
import com.retail.e_com.response.Userresponse;
import com.retail.e_com.util.Invalidotpexception;
import com.retail.e_com.util.Messagemodel;
import com.retail.e_com.util.Otpexpiredexpection;
import com.retail.e_com.util.Registrationtimeexpiered;
import com.retail.e_com.util.ResponseStructure;
import com.retail.e_com.util.RoleNotSpecifiedExceptions;
import com.retail.e_com.util.SimpleResponseStructure;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.persistence.criteria.CriteriaBuilder.Case;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthServiceimpl implements AuthService {

	private UserRepository repo;
	private CacheStorer<String> otpcache;
	private CacheStorer<User> usercache;
	private ResponseStructure<Userresponse> structure;
	private ResponseStructure<String> stringstructure;
	private SimpleResponseStructure simpleResponseStucture  ;
	private JavaMailSender mailSender;
	private MailService  mailService;

	public User maptouser(Userrequest usereq) {
		return User.builder().displayname(usereq.getName()).email(usereq.getEmail()).role(usereq.getRole())
				.password(usereq.getPassword()).build();
	}

	public Userresponse maptoresp(User user) {
		return Userresponse.builder().userId(user.getUserId()).name(user.getUsername()).email(user.getEmail())
				.role(user.getRole()).isEmailVerified(user.isEmailVerified()).isDeleted(user.isDeleted()).build();
	}

	private <T extends User> T maptochildEntity(Userrequest userreq){
		UserRole role = userreq.getRole();

		User user = null;

		switch (role) {
		case SELLER -> user = new Seller();
		case CUSTOMER -> user = new Customer();
		default ->  throw new RoleNotSpecifiedExceptions("Role is not specified");
		}
		String[] split = userreq.getEmail().split("@");

		user.setDisplayname(userreq.getName());
		user.setUsername(split[0]);
		user.setPassword(userreq.getPassword());
 		user.setRole(role);
		user.setEmail(userreq.getEmail());
		user.setEmailVerified(false);
		user.setDeleted(false);
		return (T) user;
	}


	//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	@Override
	public ResponseEntity<SimpleResponseStructure> userregistration(Userrequest userrequest) {

		if (repo.existsByEmail(userrequest.getEmail()))
			throw new RuntimeException();
		User user = maptochildEntity(userrequest);

		String otp = generateOTP();
		otpcache.add(user.getEmail(),otp);
		usercache.add(user.getEmail(),user);
		System.err.println(otp);
		try {
			sendotp(user, otp);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// User u = repo.save(user);
		// Userresponse userresp = maptoresp(u);

		return ResponseEntity.ok(simpleResponseStucture .setStatuscode(HttpStatus.ACCEPTED.value()).setMessage("Verify otp sent through mail to complete registration,otp expires in 1 minutes"));   
	}



	private String generateOTP() {
		return new DecimalFormat("000000").format(new Random().nextInt(999999));
	}



	@Override
	public ResponseEntity<ResponseStructure<Userresponse>> verifyotp(Otprequest otp) {
		if (otpcache.getdata(otp.getEmail())==null) throw new Otpexpiredexpection("Otp is expired");



		if (!otpcache.getdata(otp.getEmail()).equals(otp.getOtp()))
			throw new Invalidotpexception("please enter correct otp");

		User user = usercache.getdata(otp.getEmail());

		if(user==null)
			throw new Registrationtimeexpiered();

		user.setEmailVerified(true);
		user = repo.save(user);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(structure.setBody(maptoresp(user))
						.setStatuscode(HttpStatus.CREATED.value()));

	}

	private void sendotp(User user,String otp) throws MessagingException
	{
		Messagemodel messagemodel = Messagemodel.builder().to(user.getEmail()).subject("verify otp")
				.text("<p> Hi ,<br>  "
						+"Thanks for your intrest in E-com  "
						+"please verify your mail id using the otp given below .</p>"
						+"<br>"
						+"<h1>"+otp+"</h1>"
						+"<br>"
						+"<p>please ingnore if its not you</p>"
						+"<br>"
						+"with best regards"
						+"<h3> E-Com </h3>"
						).build();
		mailService.sendmailmessage (messagemodel);

	}


}
