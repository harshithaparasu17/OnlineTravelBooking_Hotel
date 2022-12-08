package com.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travel.entity.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer>, IHotelCustomRepository {

}
