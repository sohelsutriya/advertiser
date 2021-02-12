package com.cg.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.beans.Advertiser;
import com.cg.dao.AdvertiserDao;
import com.cg.exception.NoValueFoundException;
import com.cg.exception.NotPossibleException;

@Service
public class AdvertiserServiceImpl implements AdvertiserService {

	@Autowired
	AdvertiserDao dao;
	
	@Override
	public Advertiser addNewAdvertiser(Advertiser advertiser) {
		return this.dao.save(advertiser);
	}
	

	@Override
	public Advertiser updateInfo(Advertiser advertiser) {
		 if(advertiser==null) {
			    throw new NotPossibleException("Cannot update this newspaper");
		 }
		return this.dao.save(advertiser);
	}

	@Override
	public Advertiser getAdvertiserByUserName(String userName) {
		Optional<Advertiser> advertiser = this.dao.findAll().stream().filter(x -> x.getUserName().equals(userName)).findFirst();
		if(advertiser.isPresent()) {
			return advertiser.get();
		}
		else throw new NoValueFoundException("No Advertiser with particular userName ");
	}

	//Exception Working

	@Override
	public List<Advertiser> getAllAdvertisers() {
		List<Advertiser> getAll = this.dao.findAll();
		if(getAll.size()==0)
		{
			throw new NoValueFoundException("No Advertiser Present");

		}
		return this.dao.findAll();
	}

	@Override
	public String generateUsername() {
		Random rnd = new Random();
		int number = rnd.nextInt(9999);
		return String.format("%03d", number);
	}


	@Override
	public void sendEmail(String email, String username, String password)
			throws AddressException, MessagingException, IOException {
		  Properties props = new Properties();
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.starttls.enable", "true");
		   props.put("mail.smtp.host", "smtp.googlemail.com");
		   props.put("mail.smtp.port", "587");
		   
		   Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		      protected PasswordAuthentication getPasswordAuthentication() {
		         return new PasswordAuthentication("newsportal25@gmail.com", "News@portal");
		      }
		   });
		   Message msg = new MimeMessage(session);
		   msg.setFrom(new InternetAddress("newsportal25@gmail.com", false));

		   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
		   msg.setSubject("Login Credentials");
		   msg.setContent("Your User-Name :" +username+"\n"+"Your Password :"+password, "text/html");
		   msg.setSentDate(new Date());

		   MimeBodyPart messageBodyPart = new MimeBodyPart();
		   messageBodyPart.setContent("Your User-Name :" +username+"\n"+"Your Password :"+password, "text/html");

//		   Multipart multipart = new MimeMultipart();
//		   multipart.addBodyPart(messageBodyPart);
//		   MimeBodyPart attachPart = new MimeBodyPart();

		   //attachPart.attachFile("/var/tmp/image19.png");
		  // multipart.addBodyPart(attachPart);
		 //  msg.setContent(multipart);
		   Transport.send(msg);   

		
	}
	
}
