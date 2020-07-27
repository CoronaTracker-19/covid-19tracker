package com.covid19tracker.Controller;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.covid19tracker.Driver.Driver;
import com.covid19tracker.Driver.DriverImpl;
import com.covid19tracker.Model.Countries;
import com.covid19tracker.Model.Daywise;
import com.covid19tracker.Model.Total;
import com.covid19tracker.Service.Service1;

@RestController
@RequestMapping("/Dashboard")
public class CovidController {
	
	@Autowired
	private Service1 service;
	@Autowired
	private Driver driver;
	private List<Countries> listCountries;
		
	@PostConstruct // save data from third party API to database
	public void setAll()
	{

		//System.out.println("***********in controller***************");
		listCountries=driver.driverCountries();
		service.setAllCountries(listCountries);
		//System.out.println("*********out of controller**********");

		System.out.println("***********in controller***************");
		List<List<Daywise>> listOfListDaywise=driver.driverDaywise();
		service.setAllDaywise(listOfListDaywise);
		System.out.println("*********out of controller**********");
		
		System.out.println("***********in controller***************");
		Total total=driver.driverTotal();
		service.setAllTotal(total);
		System.out.println("*********out of controller**********");
	}
	
	@GetMapping("/Daywise/Direct")
	public List<List<Daywise>> setAllDaywise()
	{
		List<List<Daywise>> listOfListDaywise=driver.driverDaywise();
		return listOfListDaywise;
	}
	
	@GetMapping("/Active") // <--get all countries with today case,deaths,recovered and also total of this according to max active-->
	public List<Countries> getAllActive()
	{
		listCountries=service.getAllActive();
		return listCountries;
	}
	@GetMapping("/Recovered") // <--get all countries with today case,deaths,recovered and also total of this according to max recovered-->
	public List<Countries> getAllRecovered()
	{
		listCountries=service.getAllRecovered();
		return listCountries;
	}
	@GetMapping("/Deaths") // <--get all countries with today case,deaths,recovered and also total of this according to max deaths-->
	public List<Countries> getAllDeaths()
	{
		listCountries=service.getAllDeaths();
		return listCountries;
	}
	@GetMapping("/Daywise/All") // get all country with last 30 days data Datewise
	public List<Daywise> getAllDaywise()
	{
		System.out.println("***********in controller***************");
		return service.getAllDaywise();
	}
	@GetMapping("/Monthly/{countryName}")  // country input example : India (and it's return last 30 days data  with total cases,deaths,recovered and dates)
	public List<Daywise> getCountrywise(@PathVariable String countryName)
	{

		return service.getCountrywise(countryName);
	}

	@GetMapping("/Daywise/date/{dates}")  //dates input format(Month-Date-Year) example: 7-25-2020 (and it's return on that specific date's all countries with total cases, deaths,recovered)
	public List<Daywise> getDaywise(@PathVariable @DateTimeFormat(pattern = "M-dd-yyyy") Date dates)
	{
		return service.getDaywise(dates);
	}
	
	@GetMapping("/Total")
	public Total getAllTotal()
	{
		return service.getAllTotal();
	}
}
