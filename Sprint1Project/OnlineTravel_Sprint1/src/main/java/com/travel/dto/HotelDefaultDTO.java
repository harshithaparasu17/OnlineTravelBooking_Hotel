package com.travel.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class HotelDefaultDTO implements HotelDTO {

	private String hotelName;
	private int hotelNumber;
	private String location;

}
