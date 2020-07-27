package com.covid19tracker.Driver;

import java.util.List;

import com.covid19tracker.Model.Countries;
import com.covid19tracker.Model.Daywise;
import com.covid19tracker.Model.Total;

public interface Driver {
	public List<Countries> driverCountries();
	
	public List<List<Daywise>> driverDaywise();
	
	public Total driverTotal();
}
