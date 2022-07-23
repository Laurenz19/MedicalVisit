package controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import entity.Patient;
import util.HibernateUtil;

public class PatientController {
	
	public Patient getbyId(Integer id) {
		Session session = null;
		Patient patient = null;
		
		try {
			
			session = HibernateUtil.getSessionFactory().openSession();
			patient = session.get(Patient.class, id);
			
		}catch(Throwable t) {
			t.printStackTrace();
		}
		
		finally{
			if(session != null) {
				session.close();
			}
		}
		
		return patient;
	}
	
	public List<Patient> getAll() {
		Session session = null;
		List<Patient> patients = new ArrayList<Patient>();
		
		try {
			 session = HibernateUtil.getSessionFactory().openSession();
			 Query<Patient> query = session.createQuery("SELECT a FROM Patient a", Patient.class);
			 
			 patients = query.getResultList();
		}catch(Throwable t) {
			t.printStackTrace();
		}
		
		finally{
			if(session != null) {
				session.close();
			}
		}
		
		return patients;
	}
	
	public Patient create(Patient patient) {
		
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			
			session.save(patient);
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
		
		return patient;
	}
	
	public Patient update(Patient patient) {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			
			session.update(patient);
			
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		finally{
			if(session != null) {
				session.close();
			}
		}
		return patient;
	}
	
	public void delete(Patient patient) {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			session.delete(patient);
			
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
	
}
