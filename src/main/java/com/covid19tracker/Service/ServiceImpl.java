package com.covid19tracker.Service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.covid19tracker.DAO.CountriesDAO;
import com.covid19tracker.DAO.DaywiseDAO;
import com.covid19tracker.DAO.TotalDAO;
import com.covid19tracker.Model.Countries;
import com.covid19tracker.Model.Daywise;
import com.covid19tracker.Model.Total;


@Service
public class ServiceImpl implements Service1 {

	@Autowired
	private CountriesDAO countriesDAO;
	
	@Autowired
	private DaywiseDAO daywiseDAO;
	
	@Autowired
	private TotalDAO totalDAO; 
	
	@Override
	@Transactional
	public void setAllCountries(List<Countries> listCountries) {
		countriesDAO.setAll(listCountries);
	}

	@Override
	@Transactional
	public List<Countries> getAllActive() {
		return countriesDAO.getAllActive();
	}

	@Override
	@Transactional
	public List<Countries> getAllRecovered() {
		return countriesDAO.getAllRecovered();
	}

	@Override
	@Transactional
	public List<Countries> getAllDeaths() {
		return countriesDAO.getAllDeaths();
	}

	@Override
	@Transactional
	public void setAllDaywise(List<List<Daywise>> listOfListDaywise) {
		System.out.println("************in Service*************");
		daywiseDAO.setAllDaywise(listOfListDaywise);
		System.out.println("************out Service*************");

	}

	@Override
	@Transactional
	public List<Daywise> getAllDaywise() {
		System.out.println("************in Service*************");
		return daywiseDAO.getAllDaywise();
	}

	@Override
	@Transactional
	public List<Daywise> getCountrywise(String countryName) {
		System.out.println("************in Service*************");
		return daywiseDAO.getCountrywise(countryName);
	}


	@Override
	@Transactional
	public List<Daywise> getDaywise(Date dates) {
		
		return daywiseDAO.getDaywise(dates);
	}

	@Override
	@Transactional
	public void setAllTotal(Total total) {
		totalDAO.setAllTotal(total);
		
	}

	@Override
	@Transactional
	public Total getAllTotal() {
		return totalDAO.getAllTotal();
	}
	

}
