package com.retail.e_com.mailservice;

import java.util.Date;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.retail.e_com.util.Messagemodel;

import ch.qos.logback.core.model.Model;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;


@AllArgsConstructor
@Component
public class MailService {

	
	private JavaMailSender mailSender;
	
	public void sendmailmessage(Messagemodel  messagemodel) throws MessagingException
	{
	MimeMessage mimeMessage = mailSender.createMimeMessage();
	MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
	

	mimeMessageHelper.setTo(messagemodel.getTo());
	mimeMessageHelper.setSubject(messagemodel.getSubject());
	mimeMessageHelper.setSentDate(new Date());
	mimeMessageHelper.setText(messagemodel.getText(), true);
	mailSender.send(mimeMessage);
	
	}	
}
