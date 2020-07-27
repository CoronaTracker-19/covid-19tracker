package com.covid19tracker.Model;

import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown=true)
public class Total {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private long cases;
	private long todayCases;
	private long deaths;
	private long todayDeaths;
	private long recovered;
	private long todayRecovered;
	private long active;
	
	
	
	public Total() {
	}
	
	public Total(int id, long cases, long todayCases, long deaths, long todayDeaths, long recovered,
			long todayRecovered, long active) {
		this.id = id;
		this.cases = cases;
		this.todayCases = todayCases;
		this.deaths = deaths;
		this.todayDeaths = todayDeaths;
		this.recovered = recovered;
		this.todayRecovered = todayRecovered;
		this.active = active;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
		return "Total [id=" + id + ", cases=" + cases + ", todayCases=" + todayCases + ", deaths=" + deaths
				+ ", todayDeaths=" + todayDeaths + ", recovered=" + recovered + ", todayRecovered=" + todayRecovered
				+ ", active=" + active + "]";
	}
	
	
}
