package entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.xml.bind.annotation.XmlTransient;


@Entity
public class Doctor implements Serializable  {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 5193089857738219065L;
	private Integer id;
	private String code;
	private String firstname;
	private String lastname;
	private String grade;
	
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
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Set<Visit> getVisits() {
		return visits;
	}

	public void setVisits(Set<Visit> visits) {
		this.visits = visits;
	}
	
	
}
