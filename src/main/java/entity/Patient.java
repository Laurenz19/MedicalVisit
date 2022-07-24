package entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.xml.bind.annotation.XmlTransient;

@Entity
public class Patient implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8028873395891792838L;
	private Integer id;
	private String firstname;
	private String lastname;
	private char gender;
	private String address;
	
	@JsonbTransient
	@XmlTransient
	private Set<Visit> visits;
	
	public void addVisit(Visit visit) {
		this.visits.add(visit);
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Set<Visit> getVisits() {
		return visits;
	}
	public void setVisits(Set<Visit> visits) {
		this.visits = visits;
	}
	

}
