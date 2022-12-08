package com.travel.sortings;

import java.util.Comparator;

import com.travel.entity.Hotel;

public class SortHotelsFromHighToLowCost implements Comparator<Hotel> {

	@Override
	public int compare(Hotel o1, Hotel o2) {
		// TODO Auto-generated method stub
		return o2.getCostPerRoom() - o1.getCostPerRoom();
	}

}
