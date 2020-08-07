package com.covid19tracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.covid19tracker.Model.Total;

@Repository
public interface TotalsRepository extends JpaRepository<Total, Long> {


	
	List<Total> findFirstByOrderByLocalDateTimeDesc();
	
	
	
	
}
