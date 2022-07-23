package controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import entity.Doctor;
import util.HibernateUtil;

public class DoctorController {
	
	public String test() {
		Session session = null;
		String result = "";
		
		try {
			
			session = HibernateUtil.getSessionFactory().openSession();
			Object res = session.createNativeQuery("select version()").getSingleResult();
			System.out.println("result => "+ res);
			result = "result => "+ res;
		
			
		}catch(Throwable t) {
			t.printStackTrace();
		}
		
		finally{
			if(session != null) {
				session.close();
			}
		}
		return result;
	}
	
	public Doctor getbyId(Integer id) {
		Doctor doctor = null;
		Session session = null;
		
		
		try {
			
			session = HibernateUtil.getSessionFactory().openSession();
			doctor = session.get(Doctor.class, id);
			
		}catch(Throwable t) {
			t.printStackTrace();
		}
		
		finally{
			if(session != null) {
				session.close();
			}
		}
		
		return doctor;
	}
	
	
	public List<Doctor> getAll() {
		Session session = null;
		List<Doctor> doctors = new ArrayList<Doctor>();
		
		try {
			 session = HibernateUtil.getSessionFactory().openSession();
			 Query<Doctor> query = session.createQuery("SELECT a FROM Doctor a", Doctor.class);
			 
			 doctors = query.getResultList();
		}catch(Throwable t) {
			t.printStackTrace();
		}
		
		finally{
			if(session != null) {
				session.close();
			}
		}
		
		return doctors;
	}
	
	public Doctor create(Doctor doctor) {
		
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			
			session.save(doctor);
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
		
		return doctor;
	}
	
	public Doctor update(Integer id, Doctor doctor) {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			
			session.update(doctor);
			
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		finally{
			if(session != null) {
				session.close();
			}
		}
		return doctor;
	}
	
	public void delete(Doctor doctor) {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			session.delete(doctor);
			
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
