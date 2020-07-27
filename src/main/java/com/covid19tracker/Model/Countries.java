package com.covid19tracker.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="Country")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Countries {
	@Id
	@Column(name="country")
	private String country;
	@Column(name="cases")
	private long cases;
	@Column(name="today_cases")
	private long todayCases;
	@Column(name="deaths")
	private long deaths;
	@Column(name="today_deaths")
	private long todayDeaths;
	@Column(name="recovered")
	private long recovered;
	@Column(name="today_recovered")
	private long todayRecovered;
	@Column(name="active")
	private long active;
	
	
	public Countries() {
	}
	
	public Countries(String country, long cases, long todayCases, long deaths, long todayDeaths, long recovered,
			long todayRecovered, long active) {
		this.country = country;
		this.cases = cases;
		this.todayCases = todayCases;
		this.deaths = deaths;
		this.todayDeaths = todayDeaths;
		this.recovered = recovered;
		this.todayRecovered = todayRecovered;
		this.active = active;
	}
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public long getCases() {
		return cases;
	}
	public void setCases(long cases) {
		this.cases = cases;
	}
	public long getTodayCases() {
		return todayCases;
	}
	public void setTodayCases(long todayCases) {
		this.todayCases = todayCases;
	}
	public long getDeaths() {
		return deaths;
	}
	public void setDeaths(long deaths) {
		this.deaths = deaths;
	}
	public long getTodayDeaths() {
		return todayDeaths;
	}
	public void setTodayDeaths(long todayDeaths) {
		this.todayDeaths = todayDeaths;
	}
	public long getRecovered() {
		return recovered;
	}
	public void setRecovered(long recovered) {
		this.recovered = recovered;
	}
	public long getTodayRecovered() {
		return todayRecovered;
	}
	public void setTodayRecovered(long todayRecovered) {
		this.todayRecovered = todayRecovered;
	}
	public long getActive() {
		return active;
	}
	public void setActive(long active) {
		this.active = active;
	}
	
	@Override
	public String toString() {
		return "Countries [country=" + country + ", cases=" + cases + ", todayCases=" + todayCases + ", deaths="
				+ deaths + ", todayDeaths=" + todayDeaths + ", recovered=" + recovered + ", todayRecovered="
				+ todayRecovered + ", active=" + active + "]";
	}
	
	
	

}
