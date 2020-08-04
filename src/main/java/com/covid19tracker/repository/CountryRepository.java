package com.covid19tracker.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.covid19tracker.Model.Countries;

@Repository
public interface CountryRepository extends JpaRepository<Countries, String> {

	
//	@Query(value="SELECT * FROM country  ORDER BY  :type", nativeQuery = true)
//	List<Countries> getOrderby(@Param("type") String sorttype);
	
	
	
}
