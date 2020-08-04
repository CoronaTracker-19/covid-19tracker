package com.covid19tracker.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.covid19tracker.Model.Daywise;

@Repository
public interface DatewiseRepository extends JpaRepository<Daywise, Integer> {

	@Query("from Daywise  order by country, dates")
	List<Daywise> findAll();
	
	@Query("from Daywise where country=?1 and  dates>=?2 and dates<=?3")
	List<Daywise> findByCountryAndMonth(String country, String date1,String date21);
	
	@Query("from Daywise where dates>=?1 and dates<=?2")
	List<Daywise> findByMonth(String date1,String date21);
	
	@Query("from Daywise where country=?1")
	List<Daywise> findByCountry(String country);
	
	@Query("from Daywise where dates=?1")
	List<Daywise> findByDates(String dates);
	
	@Query("from Daywise where country=?1 and dates=?2")
	List<Daywise> findByCountryAndDates(String country, String dates);
}
