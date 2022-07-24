package resource.bean;

import jakarta.ws.rs.PathParam;

public class VisitBeanParam {
	private @PathParam("id") Integer id;
	private @PathParam("doctor_id") Integer doctor_id;
	private @PathParam("patient_id") Integer patient_id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDoctor_id() {
		return doctor_id;
	}
	public void setDoctor_id(Integer doctor_id) {
		this.doctor_id = doctor_id;
	}
	public Integer getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(Integer patient_id) {
		this.patient_id = patient_id;
	}
}
