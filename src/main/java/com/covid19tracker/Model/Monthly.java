package com.covid19tracker.Model;


public class Monthly {

	private String countryName;
	
	private String month;
	
	private int thisMonthCases;
	
	private int thisMonthRecovered;
	
	private int thisMonthDeaths;

	
	public Monthly() {
		
	}


	public Monthly(String countryName,String month,int thisMonthCases, int thisMonthRecovered, int thisMonthDeaths) {
		super();
		this.countryName = countryName;
		this.month=month;
		this.thisMonthCases = thisMonthCases;
		this.thisMonthRecovered = thisMonthRecovered;
		this.thisMonthDeaths = thisMonthDeaths;
	}


	public String getCountryName() {
		return countryName;
	}


	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	

	public String getMonth() {
		return month;
	}


	public void setMonth(String month) {
		this.month = month;
	}


	public int getThisMonthCases() {
		return thisMonthCases;
	}


	public void setThisMonthCases(int thisMonthCases) {
		this.thisMonthCases = thisMonthCases;
	}


	public int getThisMonthRecovered() {
		return thisMonthRecovered;
	}


	public void setThisMonthRecovered(int thisMonthRecovered) {
		this.thisMonthRecovered = thisMonthRecovered;
	}


	public int getThisMonthDeaths() {
		return thisMonthDeaths;
	}


	public void setThisMonthDeaths(int thisMonthDeaths) {
		this.thisMonthDeaths = thisMonthDeaths;
	}
	
	
	
	
}
