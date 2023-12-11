package com.voyages.beans;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 
 * @author ludiviine
 *
 */
public class Trip implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id;
	private Place departurePlace;
	private Place arrivalPlace;
	private Date departureDate;
	private Date arrivalDate;
	
	public Trip(){}
	public Trip(Place departurePlace, Place arrivalPlace, Date departureDate,
			Date arrivalDate) {
		super();
		this.departurePlace = departurePlace;
		this.arrivalPlace = arrivalPlace;
		this.departureDate = departureDate;
		this.arrivalDate = arrivalDate;
	}
	public Trip(int id, Place departurePlace, Place arrivalPlace,
			Date departureDate, Date arrivalDate) {
		super();
		this.id = id;
		this.departurePlace = departurePlace;
		this.arrivalPlace = arrivalPlace;
		this.departureDate = departureDate;
		this.arrivalDate = arrivalDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Place getDeparturePlace() {
		return departurePlace;
	}

	public void setDeparturePlace(Place departurePlace) {
		this.departurePlace = departurePlace;
	}

	public Place getArrivalPlace() {
		return arrivalPlace;
	}

	public void setArrivalPlace(Place arrivalPlace) {
		this.arrivalPlace = arrivalPlace;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	@Override
	public String toString() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/aaaa");
		return "Trip [id="
				+ id
				+ ", "
				+ (departurePlace != null ? "departurePlace=" + departurePlace
						+ ", " : "")
				+ (arrivalPlace != null ? "arrivalPlace=" + arrivalPlace + ", "
						: "")
				+ "\n\t"
				+ (departureDate != null ? "departureDate=" + departureDate//dateFormat.format(departureDate)
						+ ", " : "")
				+ (arrivalDate != null ? "arrivalDate=" + arrivalDate : "")//dateFormat.format(arrivalDate) : "")
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((arrivalDate == null) ? 0 : arrivalDate.hashCode());
		result = prime * result
				+ ((arrivalPlace == null) ? 0 : arrivalPlace.hashCode());
		result = prime * result
				+ ((departureDate == null) ? 0 : departureDate.hashCode());
		result = prime * result
				+ ((departurePlace == null) ? 0 : departurePlace.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trip other = (Trip) obj;
		if (arrivalDate == null) {
			if (other.arrivalDate != null)
				return false;
		} else if (!arrivalDate.equals(other.arrivalDate))
			return false;
		if (arrivalPlace == null) {
			if (other.arrivalPlace != null)
				return false;
		} else if (!arrivalPlace.equals(other.arrivalPlace))
			return false;
		if (departureDate == null) {
			if (other.departureDate != null)
				return false;
		} else if (!departureDate.equals(other.departureDate))
			return false;
		if (departurePlace == null) {
			if (other.departurePlace != null)
				return false;
		} else if (!departurePlace.equals(other.departurePlace))
			return false;
		return true;
	}
	
	

}
