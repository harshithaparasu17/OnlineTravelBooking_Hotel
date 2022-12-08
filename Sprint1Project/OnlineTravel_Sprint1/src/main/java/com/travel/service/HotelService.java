package com.travel.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.travel.entity.Hotel;

@Service
public interface HotelService {

	public Hotel insertHotel(Hotel hotel) throws Exception;

	public List<Hotel> getAllHotels() throws Exception;

	public Hotel getHotelByLocation(String location) throws Exception;

	public Hotel getHotelByHotelNumber(int hotelNumber) throws Exception;

	public List<Hotel> getHotelByCategory(int category) throws Exception;

	public Hotel getHotelByHotelName(String hotelName) throws Exception;

	public String delete(int hotelNumber) throws Exception;

	public List<Hotel> sortHotelsByCost() throws Exception;

	public List<Hotel> getHotelBetweenCategory(int c1, int c2) throws Exception;
}
