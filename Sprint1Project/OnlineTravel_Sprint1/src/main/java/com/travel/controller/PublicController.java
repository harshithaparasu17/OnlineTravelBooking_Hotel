package com.travel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travel.entity.Hotel;
import com.travel.service.HotelService;

@RestController
@RequestMapping("/public/travel")
public class PublicController {

	@Autowired
	HotelService hotelService;

	public PublicController() {
		System.out.println("\n\n\n=====>> Inside Constructor" + this);
	}

	@GetMapping("/welcome")
	public String abc() {
		return "Welcome to Hotel";
	}

	@GetMapping("/hotels")
	public List<Hotel> getAllHotels() {

		try {
			List<Hotel> allExtractedHotels = hotelService.getAllHotels();
			return allExtractedHotels;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	@GetMapping("/hotelname/{searchHotelName}")
	public Hotel abc3(@PathVariable String searchHotelName) throws Exception {
		searchHotelName = searchHotelName.toLowerCase();
		return hotelService.getHotelByHotelName(searchHotelName);
	}

	@GetMapping("/location/{searchLocation}")
	public Hotel abc1(@PathVariable String searchLocation) throws Exception {
		searchLocation = searchLocation.toLowerCase();
		return hotelService.getHotelByLocation(searchLocation);
	}

	@GetMapping("/category/{category}")
	public List<Hotel> abc2(@PathVariable int category) throws Exception {
		System.out.println("----> Inside controller" + category);
		return hotelService.getHotelByCategory(category);
	}

	@GetMapping("/hotelsbycategory")
	public List<Hotel> hotelsByCategory(@RequestParam int c1, @RequestParam int c2) throws Exception {
		return hotelService.getHotelBetweenCategory(c1, c2);
	}

	@GetMapping("/sort/cost")
	public List<Hotel> sortHotelsByCost() throws Exception {
		return hotelService.sortHotelsByCost();
	}
}
