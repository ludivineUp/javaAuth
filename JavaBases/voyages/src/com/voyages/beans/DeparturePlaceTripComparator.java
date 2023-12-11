package com.voyages.beans;

import java.util.Comparator;
/**
 * 
 * @author ludiviine
 *
 */
public class DeparturePlaceTripComparator implements Comparator<Trip> {

	@Override
	public int compare(Trip arg0, Trip arg1) {
		// TODO Auto-generated method stub
		return arg0.getDeparturePlace().getName().compareTo(
				arg1.getDeparturePlace().getName());
	}

}
