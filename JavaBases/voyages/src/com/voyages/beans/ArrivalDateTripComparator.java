package com.voyages.beans;

import java.util.Comparator;
/**
 * 
 * @author ludiviine
 *
 */
public class ArrivalDateTripComparator implements Comparator<Trip> {

	@Override
	public int compare(Trip o1, Trip o2) {
		// TODO Auto-generated method stub
		return o1.getArrivalDate().compareTo(o2.getArrivalDate());
	}

}
