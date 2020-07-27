package com.covid19tracker.Service;

import java.util.Date;
import java.util.List;
import com.covid19tracker.Model.Countries;
import com.covid19tracker.Model.Daywise;
import com.covid19tracker.Model.Total;


public interface Service1 {
	public void setAllCountries(List<Countries> listCountries);
	
	public List<Countries> getAllActive();
	
	public List<Countries> getAllRecovered();
	
	public List<Countries> getAllDeaths();

	public void setAllDaywise(List<List<Daywise>> listOfListDaywise);

	public List<Daywise> getAllDaywise();

	public List<Daywise> getCountrywise(String countryName);

	public List<Daywise> getDaywise(Date dates);

	public void setAllTotal(Total total);

	public Total getAllTotal();
}
