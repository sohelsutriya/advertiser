package com.cg.service;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.stereotype.Service;

import com.cg.beans.Advertiser;

public interface AdvertiserService {

	Advertiser addNewAdvertiser(Advertiser advertiser);
	Advertiser updateInfo(Advertiser advertiser);
	Advertiser getAdvertiserByUserName(String userName);
	List<Advertiser> getAllAdvertisers();
	String generateUsername();
	void sendEmail(String email,String username,String password)throws AddressException, MessagingException, IOException ;
}
    