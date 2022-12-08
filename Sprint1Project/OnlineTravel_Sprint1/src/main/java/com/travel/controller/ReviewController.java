package com.travel.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travel.dto.ErrorDTO;
import com.travel.dto.HotelDTO;
import com.travel.dto.HotelDefaultDTO;
import com.travel.entity.Hotel;
import com.travel.entity.Review;
import com.travel.service.HotelService;
import com.travel.service.ReviewService;
import com.travel.util.HotelDTOConvertor;

@RestController
@RequestMapping("hotel/review")
@Validated
public class ReviewController {
	@Autowired
	ReviewService reviewService;
	@Autowired
	HotelService hotelService;
	@Autowired
	HotelDTOConvertor dtoConvertor;

	private final Logger mylogs = LoggerFactory.getLogger(this.getClass());

	@GetMapping("/review")
	public List<Review> reviewByHotel(@RequestParam int hotelNumber) throws Exception {
		try {
			List<Review> allExtractedReviews = reviewService.getAllReviews();
			return allExtractedReviews;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	@PostMapping("/add")
	public ResponseEntity<HotelDTO> doReview(@RequestBody @Valid Review review, @RequestParam String hotelName)

	{
		Hotel alreadySavedHotel = null;

		try {
			System.out.println("---->" + mylogs);
			mylogs.info("---->>Inside try of doReview ");
			Review savedReview = reviewService.addReview(review);
			if (savedReview.getReviewId() != 0) {
				alreadySavedHotel = hotelService.getHotelByHotelName(hotelName);
				if (alreadySavedHotel != null) {
					Hotel reviewAddReview = reviewService.linkReview(savedReview, alreadySavedHotel);
					HotelDefaultDTO dto = HotelDTOConvertor.getHotelDefaultDTO(reviewAddReview);
					return new ResponseEntity<>(dto, HttpStatus.OK);
				} else {
					mylogs.error("Hotel not found in list : add");
					throw new Exception("Hotel not found " + alreadySavedHotel + "for" + hotelName);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			ErrorDTO errorDTO = new ErrorDTO(e.getMessage());
			return new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return null;
	}

}
