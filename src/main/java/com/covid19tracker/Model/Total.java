package com.covid19tracker.Model;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown=true)
public class Total {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long updates;
	private long cases;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime localDateTime;
	private long todayCases;
	private long deaths;
	private long todayDeaths;
	private long recovered;
	private long todayRecovered;
	private long active;
	
	
	
	public Total() {
	}
	

	public Total(Long updates, long cases, LocalDateTime localDateTime, long todayCases, long deaths, long todayDeaths,
			long recovered, long todayRecovered, long active) {
		super();
		this.updates = updates;
		this.cases = cases;
		this.localDateTime = localDateTime;
		this.todayCases = todayCases;
		this.deaths = deaths;
		this.todayDeaths = todayDeaths;
		this.recovered = recovered;
		this.todayRecovered = todayRecovered;
		this.active = active;
	}




	public Long getUpdates() {
		return updates;
	}

	public void setUpdates(Long updates) {
		this.updates = updates;
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
	
	
	
	
	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}


	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}


	@Override
	public String toString() {
		return "Total [updates=" + updates + ", cases=" + cases + ", localDateTime=" + localDateTime + ", todayCases="
				+ todayCases + ", deaths=" + deaths + ", todayDeaths=" + todayDeaths + ", recovered=" + recovered
				+ ", todayRecovered=" + todayRecovered + ", active=" + active + "]";
	}

	
	
}
