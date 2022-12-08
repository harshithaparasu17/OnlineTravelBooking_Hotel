package com.travel.util;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.travel.dto.HotelDefaultDTO;
import com.travel.entity.Hotel;

@Component
@Scope("singleton")
public class HotelDTOConvertor {
	public static HotelDefaultDTO getHotelDefaultDTO(Hotel hotel) {
		HotelDefaultDTO dto = new HotelDefaultDTO(hotel.getHotelName(), hotel.getHotelNumber(), hotel.getLocation());

		return dto;

	}
}
