package com.travel.service;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.entity.Hotel;
import com.travel.repository.HotelRepository;
import com.travel.sortings.SortHotelsFromHighToLowCost;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	HotelRepository hotelRepository;

	@Override
	@Transactional
	public Hotel insertHotel(Hotel hotel) throws Exception {
		Hotel savedHotel = hotelRepository.save(hotel);
		if (savedHotel != null) {
			return savedHotel;
		} else
			return null;

	}

	@Override
	public List<Hotel> getAllHotels() throws Exception {
		List<Hotel> allHotels = hotelRepository.findAll();
		return allHotels;
	}

	@Override
	public Hotel getHotelByLocation(String location) throws Exception {
		Hotel savedLoc = hotelRepository.getHotelByLocation(location);

		if (savedLoc == null) {
			throw new EntityNotFoundException(location + " not listed in the database");
		} else {
			return savedLoc;
		}
	}

	@Override
	public List<Hotel> getHotelByCategory(int category) throws Exception {
		List<Hotel> outputCategory = hotelRepository.getHotelByCategory(category);

		if (outputCategory == null) {
			throw new EntityNotFoundException(category + " not listed in the database");
		} else {
			return outputCategory;
		}
	}

	@Override
	public Hotel getHotelByHotelName(String hotelName) throws Exception {
		Hotel outputHotel = hotelRepository.getHotelByHotelName(hotelName);

		if (outputHotel == null) {
			throw new EntityNotFoundException(hotelName + " not listed in the database");
		} else {
			return outputHotel;
		}
	}

	@Override
	public String delete(int hotelNumber) throws Exception {

		hotelRepository.deleteById(hotelNumber);
		return "OPERATION IS COMPLETED";
	}

	@Override
	public List<Hotel> sortHotelsByCost() throws Exception {
		List<Hotel> list = hotelRepository.findAll();
		Collections.sort(list, new SortHotelsFromHighToLowCost());
		return list;
	}

	@Override
	public List<Hotel> getHotelBetweenCategory(int c1, int c2) throws Exception {
		return hotelRepository.getHotelsBetweenCategory(c1, c2);
	}

	@Override
	public Hotel getHotelByHotelNumber(int searchHotelNumber) throws Exception {
		Hotel outputHotel = hotelRepository.getReferenceByHotelNumber(searchHotelNumber);
		return outputHotel;
	}

}
