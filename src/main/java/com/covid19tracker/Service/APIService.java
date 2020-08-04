package com.covid19tracker.Service;

import java.text.ParseException;
import java.util.List;

import com.covid19tracker.Model.Countries;
import com.covid19tracker.Model.Daywise;
import com.covid19tracker.Model.Monthly;
import com.covid19tracker.Model.Total;
import com.covid19tracker.Model.Weekly;


public interface APIService {
	public void setAllCountries(List<Countries> listCountries);
	
	public void setAllDaywise(List<List<Daywise>> listOfListDaywise);
	
	public void setAllTotal(Total total);
	
	public  List<Monthly> getMonthly(String countryname, String month,String year) throws ParseException;
	
	public List<Countries> getAll(String parameter, String order, int pageNumber, int pageSize,String country);
	
	public List<Daywise> getDaywise(String countryname, String dates,String month,String year) throws ParseException;

	public  List<Weekly> getWeekly(String countryname, int month,int year,int weekno) throws ParseException;
	
	public List<Total> getAllTotal();

//	public List<Monthly> setMonthly(List<Monthly> monthly);
	

	

	

	

	
}
