package com.covid19tracker.Model;


public class WeeklyResponse {

	private String countryName;
	
	private int month;
	
	private int weekNo;
	
	private String weekStartDate;
	
	private String weekEndDate;
	
	private int totalCases;
	
	private int totalDeaths;
	
	private int totalRecovered;
	
	
	public WeeklyResponse() {
		
		
	}





	public WeeklyResponse(String countryName, int month, int weekNo, String weekStartDate, String weekEndDate, int totalCases,
			int totalDeaths, int totalRecovered) {
		super();
		this.countryName = countryName;
		this.month = month;
		this.weekNo = weekNo;
		this.weekStartDate = weekStartDate;
		this.weekEndDate = weekEndDate;
		this.totalCases = totalCases;
		this.totalDeaths = totalDeaths;
		this.totalRecovered = totalRecovered;
	}





	public String getCountryName() {
		return countryName;
	}


	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}


	public int getMonth() {
		return month;
	}


	public void setMonth(int month) {
		this.month = month;
	}


	public int getWeekNo() {
		return weekNo;
	}


	public void setWeekNo(int weekNo) {
		this.weekNo = weekNo;
	}


	
	public String getWeekStartDate() {
		return weekStartDate;
	}


	public void setWeekStartDate(String weekStartDate) {
		this.weekStartDate = weekStartDate;
	}

	public String getWeekEndDate() {
		return weekEndDate;
	}

	public void setWeekEndDate(String weekEndDate) {
		this.weekEndDate = weekEndDate;
	}

	public int getTotalCases() {
		return totalCases;
	}


	public void setTotalCases(int totalCases) {
		this.totalCases = totalCases;
	}


	public int getTotalDeaths() {
		return totalDeaths;
	}


	public void setTotalDeaths(int totalDeaths) {
		this.totalDeaths = totalDeaths;
	}


	public int getTotalRecovered() {
		return totalRecovered;
	}


	public void setTotalRecovered(int totalRecovered) {
		this.totalRecovered = totalRecovered;
	}
	
	
	
}
