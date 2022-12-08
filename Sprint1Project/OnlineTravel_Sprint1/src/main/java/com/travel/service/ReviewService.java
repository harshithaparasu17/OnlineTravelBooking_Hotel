package com.travel.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.travel.entity.Hotel;
import com.travel.entity.Review;

@Service
public interface ReviewService {

	Hotel linkReview(Review savedReview, Hotel alreadySavedHotel);

	Review addReview(Review review);

	List<Review> getAllReviews();

}
