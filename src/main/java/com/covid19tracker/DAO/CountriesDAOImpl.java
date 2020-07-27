package com.covid19tracker.DAO;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.covid19tracker.Model.Countries;

@Repository
public class CountriesDAOImpl implements CountriesDAO {
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public void setAll(List<Countries> listCountries) {
		//System.out.println("***************in saveAll**************");
		Session session=entityManager.unwrap(Session.class);
		
		for(Countries countries: listCountries)
			session.saveOrUpdate(countries);
		//System.out.println("******************out of saveAll***************");
	}

	@Override
	public List<Countries> getAllActive() {
		
		Session session=entityManager.unwrap(Session.class);
		
		Query<Countries> query=session.createQuery("from Countries order by active desc", Countries.class);
		
		List<Countries> listCountries=query.getResultList();
		
		return listCountries;
	}
	@Override
	public List<Countries> getAllRecovered() {
		
		Session session=entityManager.unwrap(Session.class);
		
		Query<Countries> query=session.createQuery("from Countries order by recovered desc", Countries.class);
		
		List<Countries> listCountries=query.getResultList();
		
		return listCountries;
	}
	@Override
	public List<Countries> getAllDeaths() {
		
		Session session=entityManager.unwrap(Session.class);
		
		Query<Countries> query=session.createQuery("from Countries order by deaths desc", Countries.class);
		
		List<Countries> listCountries=query.getResultList();
		
		return listCountries;
	}

}
