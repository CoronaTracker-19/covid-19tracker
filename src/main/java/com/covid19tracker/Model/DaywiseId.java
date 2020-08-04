package com.covid19tracker.Model;

import java.io.Serializable;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Embeddable
public class DaywiseId implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private String country;
	
	
	@Temporal(TemporalType.DATE)
	private Date dates;
	
	
	public DaywiseId() {
		
	}


	public DaywiseId(String country, Date dates) {
		super();
		this.country = country;
		this.dates = dates;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public Date getDates() {
		return dates;
	}


	public void setDates(Date dates) {
		this.dates = dates;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((dates == null) ? 0 : dates.hashCode());
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
		DaywiseId other = (DaywiseId) obj;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (dates == null) {
			if (other.dates != null)
				return false;
		} else if (!dates.equals(other.dates))
			return false;
		return true;
	}


	
	
	

}


