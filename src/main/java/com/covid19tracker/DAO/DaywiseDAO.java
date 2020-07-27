package com.covid19tracker.DAO;

import java.util.Date;
import java.util.List;

import com.covid19tracker.Model.Daywise;

public interface DaywiseDAO {
	public void setAllDaywise(List<List<Daywise>> listOfListDaywise);

	public List<Daywise> getAllDaywise();

	public List<Daywise> getCountrywise(String countryName);

	public List<Daywise> getDaywise(Date dates);
}
