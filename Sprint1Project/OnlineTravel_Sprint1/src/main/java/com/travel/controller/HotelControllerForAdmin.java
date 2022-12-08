package com.travel.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travel.dto.HotelDTO;
import com.travel.dto.HotelDefaultDTO;
import com.travel.entity.Hotel;
import com.travel.service.HotelService;
import com.travel.util.HotelDTOConvertor;

@RestController
@RequestMapping("/admin/travel")
@Validated
public class HotelControllerForAdmin {

	@Autowired
	HotelService hotelService;

	private final Logger mylogs = LoggerFactory.getLogger(this.getClass());

	public HotelControllerForAdmin() {
		System.out.println("\n\n\n=====>> Inside Constructor" + this);
	}

	@PostMapping("/addhotel")
	public ResponseEntity<String> addHotel(@RequestBody @Valid Hotel hotel) {

		String cityName = hotel.getLocation();
		cityName = cityName.toLowerCase();
		hotel.setLocation(cityName);

		String hotelName = hotel.getHotelName();
		hotelName = hotelName.toLowerCase();
		hotel.setHotelName(hotelName);

		System.out.println("--->> inside add hotel controller " + hotel);
		try {
			Hotel savedHotel = hotelService.insertHotel(hotel);
			String responseMessage = savedHotel.getHotelName() + "save with Number" + savedHotel.getHotelNumber();
			mylogs.info("------>> Inside Hotel Controller Post Mapping, Hotel created" + responseMessage);
			return new ResponseEntity<String>(responseMessage, HttpStatus.OK);
		} catch (Exception e) {
			String errorMessage = "Contact to customer care 1800-250-960 or mail us :- care@travel.com";
			mylogs.error(errorMessage);
			return new ResponseEntity<String>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@GetMapping("/hotel/number/{hotelNumber}")
	public ResponseEntity<HotelDTO> getHotelByHotelNumber(@PathVariable int hotelNumber) throws Exception {
		Hotel hotel = hotelService.getHotelByHotelNumber(hotelNumber);
		HotelDefaultDTO dtoResp = HotelDTOConvertor.getHotelDefaultDTO(hotel);
		return new ResponseEntity<HotelDTO>(dtoResp, HttpStatus.OK);
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

	@DeleteMapping("/deletehotel/{searchHotelNumber}")
	public String deleteHotel(@PathVariable int searchHotelNumber) {
		try {
			hotelService.delete(searchHotelNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "deleted succesfully";
	}

	@PutMapping("/update/{hotelNumber}")
	public Hotel updateHotel(@PathVariable int hotelNumber, @RequestBody Hotel hotelDetails) throws Exception {
		hotelService.getHotelByHotelNumber(hotelNumber);
		hotelDetails.setHotelNumber(hotelNumber);
		Hotel updateHotel = hotelService.insertHotel(hotelDetails);
		return updateHotel;

	}

}
