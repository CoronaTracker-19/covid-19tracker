package com.covid19tracker.Model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Daywise {

		
	@EmbeddedId
	private DaywiseId daywiseid ;
	
	private int cases;
	private int deaths;
	private int recovered;
	
	@Column(name="total_cases_begining")
	private int totalCasesBegining;
	
	@Column(name="total_recovered_begining")
	private int totalRecoveredBegining;
	
	@Column(name="total_deaths_begining")
	private int totalDeathsBegining;
	
	
	public Daywise() {
	}
	

	public Daywise(DaywiseId daywiseid, int cases, int deaths, int recovered, int totalCasesBegining,
			int totalRecoveredBegining, int totalDeathsBegining) {
		super();
		this.daywiseid = daywiseid;
		this.cases = cases;
		this.deaths = deaths;
		this.recovered = recovered;
		this.totalCasesBegining = totalCasesBegining;
		this.totalRecoveredBegining = totalRecoveredBegining;
		this.totalDeathsBegining = totalDeathsBegining;
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

	public DaywiseId getDaywiseid() {
		return daywiseid;
	}


	public void setDaywiseid(DaywiseId daywiseid) {
		this.daywiseid = daywiseid;
	}


	public int getTotalCasesBegining() {
		return totalCasesBegining;
	}


	public void setTotalCasesBegining(int totalCasesBegining) {
		this.totalCasesBegining = totalCasesBegining;
	}


	public int getTotalRecoveredBegining() {
		return totalRecoveredBegining;
	}


	public void setTotalRecoveredBegining(int totalRecoveredBegining) {
		this.totalRecoveredBegining = totalRecoveredBegining;
	}


	public int getTotalDeathsBegining() {
		return totalDeathsBegining;
	}


	public void setTotalDeathsBegining(int totalDeathsBegining) {
		this.totalDeathsBegining = totalDeathsBegining;
	}

		
	
}
