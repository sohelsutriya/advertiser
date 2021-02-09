package com.cg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.beans.Advertiser;
import com.cg.dao.AdvertiserDao;
import com.cg.service.AdvertiserService;
import com.cg.service.AdvertiserServiceImpl;

@SpringBootTest
class AdvertiserApplicationTests {

	@Autowired
	private AdvertiserServiceImpl service;
	
	@MockBean
	private AdvertiserDao repository;
	
	@Test
	void contextLoads() {
	}
	@Test
	void testAddAdvertisement() {
		Advertiser addAdvertiser=new Advertiser(1,"Aashi","7262087411","aashi@gmail.com","Pune","aashi","aashi@123");
		AdvertiserService advertiserservice= mock(AdvertiserServiceImpl.class);
		when(advertiserservice.addNewAdvertiser(addAdvertiser)).thenReturn(addAdvertiser);
		System.out.println(addAdvertiser.toString());
		Advertiser logp =advertiserservice.addNewAdvertiser(addAdvertiser) ;
		assertEquals(addAdvertiser, logp);
		
	}
	
	@Test
	void testUpdateInfo() {

		Advertiser addItem=new Advertiser(1,"Aashi","7262087411","aashi@gmail.com","Pune","aashi","aashi@123");
		AdvertiserService advertisementservice= mock(AdvertiserServiceImpl.class);
		when(advertisementservice.updateInfo(addItem)).thenReturn(addItem);
		System.out.println(addItem.toString());
		Advertiser logp = advertisementservice.updateInfo(addItem);
		assertEquals(addItem, logp);

	}

	
	  @Test void viewAllAdvertiser() {

	       List<Advertiser>advertiser=new ArrayList<Advertiser>(); 
	       advertiser.add(new Advertiser());
	       AdvertiserServiceImpl advertisementservice=mock(AdvertiserServiceImpl.class);
	 
	       when(advertisementservice.getAllAdvertisers()).thenReturn(advertiser);
	       System.out.println(advertiser.toString()); 
	       List<Advertiser> loge =advertisementservice.getAllAdvertisers(); 
	       assertEquals(advertiser, loge);
	 
	      
	      }

}
