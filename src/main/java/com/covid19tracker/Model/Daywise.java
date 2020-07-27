package com.covid19tracker.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Daywise {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String country;
	private String dates;
	private int cases;
	private int deaths;
	private int recovered;
	public Daywise() {
	}
	public Daywise(int id, String country, String dates, int cases, int deaths, int recovered) {
		this.id = id;
		this.country = country;
		this.dates = dates;
		this.cases = cases;
		this.deaths = deaths;
		this.recovered = recovered;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getDates() {
		return dates;
	}
	public void setDates(String dates) {
		this.dates = dates;
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
		
	
}
