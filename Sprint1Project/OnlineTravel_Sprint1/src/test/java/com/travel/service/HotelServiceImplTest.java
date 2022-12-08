package com.travel.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.travel.entity.Hotel;
import com.travel.repository.HotelRepository;

@SpringBootTest
class HotelServiceImplTest {

	@Mock
	HotelRepository hotelRepository;

	@InjectMocks
	HotelServiceImpl hotelService;

	@Test
	@DisplayName("Test to verify insert operation")
	// @Disabled
	void testInsertHotel() throws Exception {
		// given
		Hotel sampleInput = new Hotel("Hotel Bliss", "Chennai", 5);
		Hotel expectedOutput = new Hotel("Hotel Bliss", "Chennai", 5);

		BDDMockito.given(hotelService.insertHotel(sampleInput)).willReturn(expectedOutput);
		// when
		Hotel actualOutput = hotelService.insertHotel(sampleInput);
		// verify
		assertEquals(expectedOutput, actualOutput);

	}

	@Test
	@DisplayName("Test to verify all hotels are retrived")
	// @Disabled
	void testGetAllHotels() throws Exception {
		// given

		Hotel hotel1 = new Hotel("Hotel Bliss", "Chennai", 5);
		hotel1.setHotelReviews(null);
		Hotel hotel2 = new Hotel("Holiday Spot", "Mysore", 4);
		hotel2.setHotelReviews(null);

		List<Hotel> sampleOutput = new ArrayList<>();
		sampleOutput.add(hotel1);
		sampleOutput.add(hotel2);

		BDDMockito.given(hotelService.getAllHotels()).willReturn(sampleOutput);
		// -----------------------------------------------

		// when:- ready to test the functionality(service)
		List<Hotel> actualOutput = hotelService.getAllHotels();

		// the: verify the sampleOutput with actual Output
		assertNotNull(actualOutput);
		assertThat(actualOutput.size()).isGreaterThan(0);
		assertIterableEquals(sampleOutput, actualOutput);

	}

	@Test
	@DisplayName("Test to check Hotels between category")
	// @Disabled
	void testGetHotelBetweenCategory() throws Exception {
		// given
		Hotel hotel1 = new Hotel("Park-Inn", "Delhi", 5);
		hotel1.setHotelReviews(null);
		Hotel hotel2 = new Hotel("Holiday-spot", "Mysore", 3);
		hotel2.setHotelReviews(null);
		List<Hotel> sampleOutput = new ArrayList<>();
		sampleOutput.add(hotel1);
		sampleOutput.add(hotel2);
		int sampleInput1 = 1;
		int sampleInput2 = 8;
		BDDMockito.given(hotelService.getHotelBetweenCategory(sampleInput1, sampleInput2)).willReturn(sampleOutput);
		// when
		List<Hotel> actualOutput = hotelService.getHotelBetweenCategory(sampleInput1, sampleInput2);
		// verify
		assertEquals(sampleOutput, actualOutput);
		assertIterableEquals(sampleOutput, actualOutput);
		assertNotNull(actualOutput);
	}

	@Test
	@DisplayName("Test to verify the method getHotel by Number returns Hotel or not")
	// @Disabled
	void testGetHotelByHotelNumber() throws Exception {
		// given
		Hotel sampleOutput = new Hotel("Southernland", "Coimbatore", 3);
		sampleOutput.setHotelNumber(1);
		int sampleInput = 1;
		BDDMockito.given(hotelService.getHotelByHotelNumber(sampleInput)).willReturn(sampleOutput);
		// when
		Hotel actualOutput = hotelService.getHotelByHotelNumber(sampleInput);
		// verify
		assertEquals(sampleOutput, actualOutput);
		assertSame(sampleOutput.getHotelNumber(), actualOutput.getHotelNumber());
		assertThat(actualOutput.getHotelNumber()).isGreaterThan(0);
	}

	

}
