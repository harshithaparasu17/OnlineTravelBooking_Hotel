package com.travel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.travel.entity.Hotel;

@Repository
public interface IHotelCustomRepository {
	public Hotel getHotelByHotelName(String hotelName) throws Exception;

	public Hotel getHotelByLocation(String location) throws Exception;

	public Hotel getReferenceByHotelNumber(int searchHotelNumber);

	public List<Hotel> getHotelByCategory(int category) throws Exception;

	@Query("from Hotel where category >= :c1 and category <= :c2")
	public List<Hotel> getHotelsBetweenCategory(@Param("c1") int c1, @Param("c2") int c2) throws Exception;
}
