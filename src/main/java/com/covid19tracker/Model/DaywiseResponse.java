package com.covid19tracker.Model;

import java.util.Date;



public class DaywiseResponse {

	private String countryName;
	
	private Date date;
	
	private int cases;
	private int deaths;
	private int recovered;
	
	
	private int totalCasesFrmBegining;
	
	
	private int totalRecoveredFrmBegining;
	
	
	private int totalDeathsFrmBegining;
	
	public DaywiseResponse() {
		
	}

	public DaywiseResponse(String countryName, Date date, int cases, int deaths, int recovered, int totalCasesFrmBegining,
			int totalRecoveredFrmBegining, int totalDeathsFrmBegining) {
		super();
		this.countryName = countryName;
		this.date = date;
		this.cases = cases;
		this.deaths = deaths;
		this.recovered = recovered;
		this.totalCasesFrmBegining = totalCasesFrmBegining;
		this.totalRecoveredFrmBegining = totalRecoveredFrmBegining;
		this.totalDeathsFrmBegining = totalDeathsFrmBegining;
	}

	
	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getCases() {
		return cases;
	}

	public void setCases(int cases) {
		this.cases = cases;
	}

	public int getDeaths() {
		return deaths;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

	public int getRecovered() {
		return recovered;
	}

	public void setRecovered(int recovered) {
		this.recovered = recovered;
	}

	public int getTotalCasesFrmBegining() {
		return totalCasesFrmBegining;
	}

	public void setTotalCasesFrmBegining(int totalCasesFrmBegining) {
		this.totalCasesFrmBegining = totalCasesFrmBegining;
	}

	public int getTotalRecoveredFrmBegining() {
		return totalRecoveredFrmBegining;
	}

	public void setTotalRecoveredFrmBegining(int totalRecoveredFrmBegining) {
		this.totalRecoveredFrmBegining = totalRecoveredFrmBegining;
	}

	public int getTotalDeathsFrmBegining() {
		return totalDeathsFrmBegining;
	}

	public void setTotalDeathsFrmBegining(int totalDeathsFrmBegining) {
		this.totalDeathsFrmBegining = totalDeathsFrmBegining;
	}
	
	
}
