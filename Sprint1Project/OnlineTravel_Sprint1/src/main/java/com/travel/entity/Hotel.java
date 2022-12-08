package com.travel.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hotels")
public class Hotel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int hotelNumber;
	@NotNull(message = "Hotel Name should not be null")
	private String hotelName;
	private String location;
	private int category;
	private int noOfRooms;
	private int noOfRoomsFree;
	private int bookedRooms; 
	private int avgUserRating;
	private int costPerRoom;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "hotelInfo")
	List<Review> hotelReviews;

	public Hotel(@NotNull(message = "Hotel Name should not be null")String hotelName, String location, int category, int noOfRooms, int noOfRoomsFree, int bookedRooms,
			int avgUserRating, int costPerRoom) {
		super();
		this.hotelName = hotelName;
		this.location = location;
		this.category = category;
		this.noOfRooms = noOfRooms;
		this.noOfRoomsFree = noOfRoomsFree;
		this.bookedRooms = bookedRooms;
		this.avgUserRating = avgUserRating;
		this.costPerRoom = costPerRoom;
	}

	public Hotel(@NotNull(message = "Hotel Name should not be null") String hotelName, String location, int category) {
		super();
		this.hotelName = hotelName;
		this.location = location;
		this.category = category;
	}

}
