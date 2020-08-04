package com.covid19tracker.Controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.covid19tracker.Driver.Driver;
import com.covid19tracker.ExceptionHandler.CountryNotFoundException;
import com.covid19tracker.ExceptionHandler.DataNotFoundException;
import com.covid19tracker.ExceptionHandler.InvalidCountryNumberException;
import com.covid19tracker.ExceptionHandler.InvalidMonthException;
import com.covid19tracker.ExceptionHandler.InvalidOrderException;
import com.covid19tracker.ExceptionHandler.InvalidParameterException;
import com.covid19tracker.ExceptionHandler.InvalidWeekNoException;
import com.covid19tracker.ExceptionHandler.InvalidYearException;
import com.covid19tracker.Model.Countries;
import com.covid19tracker.Model.DaywiseId;
import com.covid19tracker.Model.Daywise;
import com.covid19tracker.Model.Monthly;
import com.covid19tracker.Model.Total;
import com.covid19tracker.Model.Weekly;
import com.covid19tracker.Service.APIService;

@RestController
@RequestMapping("/dashboard")
public class CovidController {
	
	@Autowired
	private APIService service;
	@Autowired
	private Driver driver;
	private List<String> listMonth;
	
	
	private List<Countries> listCountries;
	private List<List<Daywise>> listOfListDaywise; 
	@PostConstruct // save data from third party API to database
	public void setAll()
	{

		System.out.println("***********in controller***************");
		listCountries=driver.driverCountries();
		service.setAllCountries(listCountries);
		System.out.println("*********out of controller**********");

		System.out.println("***********in controller***************");
		listOfListDaywise=driver.driverDaywise();
		service.setAllDaywise(listOfListDaywise);
//		service.getMonthly(listOfListDaywise);
		System.out.println("*********out of controller**********");
	
		System.out.println("***********in controller***************");
		Total total=driver.driverTotal();
		service.setAllTotal(total);
		System.out.println("*********out of controller**********");
		
		
		listMonth=new ArrayList<>();
		
		listMonth.add("January"); listMonth.add("February"); listMonth.add("March");
		listMonth.add("April"); listMonth.add("May"); listMonth.add("June");
		listMonth.add("July"); listMonth.add("August"); listMonth.add("September");
		listMonth.add("October"); listMonth.add("November"); listMonth.add("December");
	}
	
	@GetMapping("/cases")
	public List<Countries> getCountry(
			@RequestParam(name = "parameter", defaultValue = "cases", required = false) String parameter,
			@RequestParam(name = "order", defaultValue = "desc", required = false) String order,
			@RequestParam(name = "pagenumber", defaultValue = "0", required = false) int pageNumber,
			@RequestParam(name="pagesize", defaultValue="215", required=false) int pageSize,
			@RequestParam(name="country", defaultValue="all", required=false) String country) {

		
		if(country.contentEquals("all"))
		{
			if((pageSize>215 || pageSize<=0) || (pageNumber>215 || pageNumber<0))
			{
				throw new InvalidCountryNumberException("Either size "+pageSize+" or number "+pageNumber+" is invalid");
			}
			else if(!(parameter.contentEquals("country") || parameter.contentEquals("cases") ||
					parameter.contentEquals("todayCases") || parameter.contentEquals("deaths") ||
					parameter.contentEquals("todayDeaths") || parameter.contentEquals("recovered") ||
					parameter.contentEquals("todayRecovered") || parameter.contentEquals("active")))
			{
				throw new InvalidParameterException(parameter+" is invalid parameter");
			}
			else if(!(order.equalsIgnoreCase("desc")|| order.equalsIgnoreCase("asc")))
			{
				throw new InvalidOrderException(order+" is invalid order type");
			}
		}
		else
		{			
			for(Countries countries: listCountries)
			{
				//System.out.println(countries.getCountry());
				if(country.equalsIgnoreCase(countries.getCountry()))
				{
					return service.getAll(parameter, order, pageNumber, pageSize, country);
				}
			}
			throw new CountryNotFoundException("The country "+country+" is not acceptable");
		}
		return service.getAll(parameter, order, pageNumber, pageSize, country);		
		
//		listCountries = service.getAll(parameter, order, pageNumber, pageSize);
//		return listCountries;

	}

	
	
	@GetMapping("/country")
	public List<Daywise> getCountrywise(
			@RequestParam(name = "countryname", defaultValue = "all", required = false) String countryname,
			@RequestParam(name = "date", defaultValue = "empty", required = false) String dates,
			@RequestParam(name = "month", defaultValue = "empty", required = false) String month,
			@RequestParam(name = "year", defaultValue = "2020", required = false) String year) throws ParseException {
		
		int yearnum = Integer.parseInt(year);

		if (yearnum < 2020) {
			throw new InvalidYearException(year + " is invalid year");
		} else if (countryname.equalsIgnoreCase("all")) {
			if (!month.equalsIgnoreCase("empty")) {
				System.out.println("in month");
				for (String monthvalue : listMonth) {
					if (month.equalsIgnoreCase(monthvalue)) {
						return service.getDaywise(countryname, dates, month, year);
					}
				}
				throw new InvalidMonthException(month + " is invalid month");
			}
		} else {
			int i = 0;
			System.out.println("in month");
			for (String monthvalue : listMonth) {
				if (month.equalsIgnoreCase(monthvalue) || month.equalsIgnoreCase("empty")) {
					i++;
				}
			}
			if (i != 0) {
				System.out.println("in country");
				for (List<Daywise> listdaywise : listOfListDaywise) {
					for (Daywise daywise : listdaywise) {
						if (countryname.equalsIgnoreCase(daywise.getDaywiseid().getCountry())) {
							// System.out.println(month+""+dates+""+countryname);
							return service.getDaywise(countryname, dates, month, year);
						}
					}
				}
				throw new CountryNotFoundException("The country " + countryname + " is not acceptable");
			}
			throw new InvalidMonthException(month + " is invalid month");
		}
		return service.getDaywise(countryname, dates, month, year);

	}
	
	@GetMapping("/monthly")
	public List<Monthly> getMonthly(
			@RequestParam(name = "countryname", defaultValue = "india", required = false) String countryname,
			@RequestParam(name = "month", defaultValue = "July", required = false) String month,
			@RequestParam(name = "year", defaultValue = "2020", required = false) String year) throws ParseException {

		int yearnum = Integer.parseInt(year);

		

		if (yearnum < 2020)
		{
			throw new InvalidYearException(year + " is invalid year or not present in database");
		}
		else
		{
			System.out.println("in month");
			int i=0;
			for(String monthvalue: listMonth)
			{
				if(month.equalsIgnoreCase(monthvalue) || month.equalsIgnoreCase("empty"))
				{
					i++;
				}
			}
			if(i==0)
				throw new InvalidMonthException(month+" is invalid month");
			System.out.println("in country");
			for (List<Daywise> listdaywise : listOfListDaywise) 
			{
				for (Daywise daywise : listdaywise)
				{
					if (countryname.equalsIgnoreCase(daywise.getDaywiseid().getCountry()))
					{
						// System.out.println(month+""+dates+""+countryname);
						return service.getMonthly(countryname, month, year);
					}
				}
			}
			throw new CountryNotFoundException("The country " + countryname + " is not acceptable");
		}
		

	}
	
	@GetMapping("/weekly")
	public List<Weekly> getWeekly(
			@RequestParam(name = "countryname", defaultValue = "india", required = false) String countryname,
			@RequestParam(name = "month", defaultValue = "7", required = false) int month,
			@RequestParam(name = "year", defaultValue = "2020", required = false) int year,
			@RequestParam(name = "weekno", defaultValue = "1", required = false) int weekno) throws ParseException {

		if(year<2020 )
		{
			throw new InvalidYearException(year+" is invalid year");
		}
		else if(weekno<1 || weekno>5)
		{
			throw new InvalidWeekNoException(weekno+" is invalid");
		}
		else
		{	
			System.out.println("in country");
				for(List<Daywise> listdaywise: listOfListDaywise)
				{
					for(Daywise daywise: listdaywise)
					{
						if(countryname.equalsIgnoreCase(daywise.getDaywiseid().getCountry()))
						{
							//System.out.println(month+""+dates+""+countryname);
							return service.getWeekly(countryname, month,year,weekno);
						}
					}
				}
			throw new CountryNotFoundException("The country "+countryname+" is not acceptable");
		}
		

	}
	@GetMapping("/total")
	public List<Total> getTotal()
	{
		return service.getAllTotal();
	}
}
