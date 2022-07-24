package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import entity.Doctor;
import entity.Patient;
import entity.Visit;
import util.HibernateUtil;

public class VisitController {
	
	
	public List<Visit> getAll() {
		Session session = null;
		List<Visit> _visits = new ArrayList<Visit>();
		
		try {
			 session = HibernateUtil.getSessionFactory().openSession();
			 Query<Visit> query = session.createQuery("SELECT a FROM Visit a", Visit.class);
			 
			 List<Visit> visits = query.getResultList();
			 
			 for(Visit visit : visits){
				  _visits.add(this.getbyId(visit.getId()));
			 }
			 
		}catch(Throwable t) {
			t.printStackTrace();
		}
		
		finally{
			if(session != null) {
				session.close();
			}
		}
		
		return _visits;
	}
	
	public Visit getbyId(Integer id) {
		Visit _visit = new Visit();
		Doctor doctor = new Doctor();
		Patient patient = new Patient();
		Session session = null;
		
		
		try {
			
			session = HibernateUtil.getSessionFactory().openSession();
			Visit visit = session.get(Visit.class, id);
			
			doctor.setId(visit.getDoctor().getId());
			doctor.setCode(visit.getDoctor().getCode());
			doctor.setFirstname(visit.getDoctor().getFirstname());
			doctor.setLastname(visit.getDoctor().getLastname());
			doctor.setGrade(visit.getDoctor().getGrade());
			
			
			patient.setId(visit.getPatient().getId());
			patient.setFirstname(visit.getPatient().getFirstname());
			patient.setLastname(visit.getPatient().getLastname());
			patient.setGender(visit.getPatient().getGender());
			patient.setAddress(visit.getPatient().getAddress());
			
			
			
			_visit.setDoctor(doctor);
			_visit.setPatient(patient);
			_visit.setDate(visit.getDate());
			
		}catch(Throwable t) {
			t.printStackTrace();
		}
		
		finally{
			if(session != null) {
				session.close();
			}
		}
		
		return _visit;
	}
	
	public Visit create(Visit visit) {
		
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			
			session.save(visit);
			session.flush();
			
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		finally{
			if(session != null) {
				session.close();
			}
		}
		
		visit = this.getbyId(visit.getId());
		return visit;
	}
	
	public Visit update(Visit visit) {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			
			session.update(visit);
			
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		finally{
			if(session != null) {
				session.close();
			}
		}
		visit = this.getbyId(visit.getId());
		return visit;
	}
	
	public void delete(Visit visit) {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			session.delete(visit);
			
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		finally{
			if(session != null) {
				session.close();
			}
		}
		
	}
	
	public String formatDate(Date date) {
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		
		return simpleDateFormat.format(date);
	}

}
