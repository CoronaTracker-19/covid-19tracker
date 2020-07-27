package com.covid19tracker.DAO;

import java.util.List;

import com.covid19tracker.Model.Countries;

public interface CountriesDAO {
	
	public void setAll(List<Countries> listCountries);

	public List<Countries> getAllActive();
	
	public List<Countries> getAllRecovered();
	
	public List<Countries> getAllDeaths();

}
