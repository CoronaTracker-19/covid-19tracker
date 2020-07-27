package com.covid19tracker.DAO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.covid19tracker.Model.Daywise;

@Repository
public class DaywiseDAOImpl implements DaywiseDAO {

	
	private EntityManager entityManager;
	
	@Autowired
	public DaywiseDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void setAllDaywise(List<List<Daywise>> listOfListDaywise) {
		
		
		Session session=entityManager.unwrap(Session.class);
		
		for(List<Daywise> listDaywise: listOfListDaywise)
		{
			
			for(Daywise daywise: listDaywise)
			{	
				
//				System.out.println(daywise);
				session.save(daywise);
			}
		}
		System.out.println("****************out DAO******************");
	}

	@Override
	public List<Daywise> getAllDaywise() {
		System.out.println("****************in DAO******************");
		
		Session session=entityManager.unwrap(Session.class);
		
		Query<Daywise> query=session.createQuery("from Daywise order by country asc, dates asc", Daywise.class);
		
		List<Daywise> listDaywise=query.getResultList();
		
		System.out.println("****************out DAO******************");
		
		return listDaywise;
		
	}

	@Override
	public List<Daywise> getCountrywise(String countryName) {
		
		System.out.println("****************in DAO******************");
		Session session=entityManager.unwrap(Session.class);
		
		
		String hql="from Daywise D where D.country='"+countryName+"' order by dates";
		
		Query<Daywise> query=session.createQuery(hql, Daywise.class);
		
		List<Daywise> listCountrywise=query.getResultList();
		
		System.out.println("****************out DAO******************");
		
		return listCountrywise;
	}

	@Override
	public List<Daywise> getDaywise(Date dates) {
		Session session=entityManager.unwrap(Session.class);
		
		
		Query<Daywise> query=session.createQuery("from Daywise where dates=:Dates order by country",Daywise.class);
		
		SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yy");
		String date = sdf.format(dates); 
		
		System.out.println(date);
		
		query.setParameter("Dates", date);
		
		
		List<Daywise> listDaywise=query.getResultList();
		
		return listDaywise;
	}
			
	
}
