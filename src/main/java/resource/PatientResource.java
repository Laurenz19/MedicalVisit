package resource;

import java.util.List;

import controller.PatientController;
import entity.Patient;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import resource.bean.PatientBeanParam;
import resource.exception.AppException;

@Path("patient")
@Produces(value= { MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
public class PatientResource {
	
	private PatientController pc = new PatientController();
	
	@GET
	public Response getPatients() {
		List<Patient> patients = pc.getAll();
		Status status = Status.NO_CONTENT;
		
		if(patients.size()>0) {
			status = Status.OK;
		}
		
		return Response.status(status)
				.entity(patients)
				.build();
	}
	
	@GET
	@Path("/{id}")
	public Response getPatientbyId(@BeanParam PatientBeanParam ptParam) {
		Patient patient = pc.getbyId(ptParam.getId());
		
		if(patient == null) {
			throw new AppException("Data not found", 404);
		}
		
		return Response.status(200)
				.entity("Test worked")
				.build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createPatient(Patient patient) {
		patient = pc.create(patient);
		
		return Response.status(200)
				.entity(patient)
				.build();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updatePatient(Patient patientBody, @BeanParam PatientBeanParam ptParam) {
		Patient patient = pc.getbyId(ptParam.getId());
		
		if(patient == null) {
			throw new AppException("Data not found", 404);
		}
		
		patient.setFirstname(patientBody.getFirstname());
		patient.setLastname(patientBody.getLastname());
		patient.setGender(patientBody.getGender());
		patient.setAddress(patientBody.getAddress());
		
		patient = pc.update(patient);
		
		return Response.status(200)
				.entity(patient)
				.build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response deletePatient(@BeanParam PatientBeanParam ptParam) {
		Patient patient = pc.getbyId(ptParam.getId());
		
		if(patient == null) {
			throw new AppException("Data not found", 404);
		}
		pc.delete(patient);
		
		return Response.status(200)
				.build();
	}
	

}
