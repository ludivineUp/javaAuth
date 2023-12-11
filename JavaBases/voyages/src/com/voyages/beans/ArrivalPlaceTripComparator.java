package com.voyages.beans;

import java.util.Comparator;
/**
 * 
 * @author ludiviine
 *
 */
public class ArrivalPlaceTripComparator implements Comparator<Trip> {

	@Override
	public int compare(Trip arg0, Trip arg1) {
		// TODO Auto-generated method stub
		return arg0.getArrivalPlace().compareTo(arg1.getArrivalPlace());
	}

}
