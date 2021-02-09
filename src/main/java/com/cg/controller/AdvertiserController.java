package com.cg.controller;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.beans.Advertiser;
import com.cg.service.AdvertiserServiceImpl;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping(value = "/advertiser")
public class AdvertiserController {
	
	@Autowired
	private AdvertiserServiceImpl service;
	
	//http://localhost:7171/advertiser/add
	@PostMapping(value= "/add",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Advertiser addNewAdvertiser(@RequestBody Advertiser advertiser) throws AddressException, MessagingException, IOException {
		advertiser.setUserName(advertiser.getCustomerName()+service.generateUsername());
		//System.out.println(advertiser.getCustomerName()+service.generateUsername());
		this.service.sendEmail(advertiser.getCustomerEmail(),advertiser.getUserName(),advertiser.getPassword());
		return this.service.addNewAdvertiser(advertiser);
	}
	
	@PutMapping(value= "/update/{userName}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Advertiser updateAdvertiser(@RequestBody Advertiser advertiser,@PathVariable String userName) {
		Advertiser advertise = this.service.updateInfo(advertiser);
		
		advertise.setCustomerName(advertise.getCustomerName());
		advertise.setCustomerPhone(advertise.getCustomerPhone());
		advertise.setCustomerAddress(advertise.getCustomerAddress());
		advertise.setCustomerEmail(advertise.getCustomerEmail());
		return this.service.updateInfo(advertise);
	}
	
	//http://localhost:7171/advertiser/get/
	@GetMapping(value= "/get/{userName}")
	public Advertiser getAdvertiser(@PathVariable String userName) {
		return this.service.getAdvertiserByUserName(userName);
	}
	
	@GetMapping(value= "/getAll")
	public List<Advertiser> getAllAdvertisers() {
		return this.service.getAllAdvertisers();
	}

}
