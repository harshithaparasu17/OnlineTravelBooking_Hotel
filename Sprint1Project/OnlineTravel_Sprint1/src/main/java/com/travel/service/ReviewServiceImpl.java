package com.travel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.travel.entity.Hotel;
import com.travel.entity.Review;
import com.travel.repository.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService {
	@Autowired
	ReviewRepository reviewRepository;

	@Override
	public Hotel linkReview(Review savedReview, Hotel alreadySavedHotel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Review addReview(Review review) {
		Review savedReview = reviewRepository.save(review);
		return savedReview;
	}

	@Override
	public List<Review> getAllReviews() {
		// TODO Auto-generated method stub
		return null;
	}

}
