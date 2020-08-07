package com.covid19tracker.Model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TotalResponse {
	
	
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime localDateTime;
	private long todayCases;
	private long todayRecovered;
	private long todayDeaths;
	private long totalCases;
	private long totalActive;
	private long totalRecovered;
	private long totalDeaths;
	
	
	public TotalResponse() {
		
	}


	public TotalResponse(LocalDateTime localDateTime, long todayCases, long todayRecovered,
			long todayDeaths,long totalCases,  long totalActive, long totalRecovered, long totalDeaths) {
		super();
		
		this.localDateTime = localDateTime;
		this.todayCases = todayCases;
		this.todayRecovered = todayRecovered;
		this.todayDeaths = todayDeaths;
		this.totalCases = totalCases;
		this.totalActive = totalActive;
		this.totalRecovered = totalRecovered;
		this.totalDeaths = totalDeaths;
	}




	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}


	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}


	public long getTodayCases() {
		return todayCases;
	}


	public void setTodayCases(long todayCases) {
		this.todayCases = todayCases;
	}


	public long getTodayRecovered() {
		return todayRecovered;
	}


	public void setTodayRecovered(long todayRecovered) {
		this.todayRecovered = todayRecovered;
	}


	public long getTodayDeaths() {
		return todayDeaths;
	}


	public void setTodayDeaths(long todayDeaths) {
		this.todayDeaths = todayDeaths;
	}


	public long getTotalCases() {
		return totalCases;
	}


	public void setTotalCases(long totalCases) {
		this.totalCases = totalCases;
	}
	
	public long getTotalActive() {
		return totalActive;
	}


	public void setTotalActive(long totalActive) {
		this.totalActive = totalActive;
	}


	public long getTotalRecovered() {
		return totalRecovered;
	}


	public void setTotalRecovered(long totalRecovered) {
		this.totalRecovered = totalRecovered;
	}


	public long getTotalDeaths() {
		return totalDeaths;
	}


	public void setTotalDeaths(long totalDeaths) {
		this.totalDeaths = totalDeaths;
	}
	
	
}
