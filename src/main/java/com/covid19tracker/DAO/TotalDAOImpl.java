package com.covid19tracker.DAO;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.covid19tracker.Model.Total;

@Repository
public class TotalDAOImpl implements TotalDAO {

	@Autowired
	EntityManager entityManager;
	
	@Override
	public void setAllTotal(Total total) {
		
		Session session=entityManager.unwrap(Session.class);
		
		session.save(total);

	}

	@Override
	public Total getAllTotal() {
		Session session=entityManager.unwrap(Session.class);
		
		int id=1;
		
		Total total=session.get(Total.class, id);
		
		return total;
	}


}
