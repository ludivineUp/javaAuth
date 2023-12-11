package com.voyages.beans;

import java.util.Comparator;
/**
 * 
 * @author ludiviine
 *
 */
public class DepartureDateTripComparator implements Comparator<Trip> {

	@Override
	public int compare(Trip o1, Trip o2) {
		// TODO Auto-generated method stub
		return (int) (o2.getDepartureDate().getTime() - o1.getDepartureDate().getTime());
	}

}
