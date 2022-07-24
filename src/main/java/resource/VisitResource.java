package resource;

import java.util.Date;
import java.util.List;

import controller.DoctorController;
import controller.PatientController;
import controller.VisitController;
import entity.Doctor;
import entity.Patient;
import entity.Visit;
import entity.VisitForm;
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
import resource.bean.VisitBeanParam;
import resource.exception.AppException;

@Path("visit")
@Produces( value= { MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public class VisitResource {
	
	private VisitController vc = new VisitController();
	private DoctorController dc = new DoctorController();
	private PatientController pc = new PatientController();

	@GET
	@Path("/serialize")
	public Response test() {
		Doctor doctor = new Doctor();
		Patient patient = new Patient();
		Visit visit = new Visit();
		
		doctor.setCode("DC01");
		doctor.setFirstname("Sambany");
		doctor.setLastname("Laurenzio");
		doctor.setGrade("Dev");
		
		
		patient.setFirstname("Sambany");
		patient.setLastname("Michel");
		patient.setGender('M');
		patient.setAddress("Toamasina");
		
		visit.setPatient(patient);
		visit.setDoctor(doctor);
		visit.setDate(new Date());
		
		
		return Response.status(200)
				.entity(visit)
				.build();
	}
	
	@GET
	public Response getVisits() {
		List<Visit> visits = vc.getAll();
		Status status = Status.NO_CONTENT;
		
		if(visits.size()>0) {
			status = Status.OK;
		}
		
		return Response.status(status)
				.entity(visits)
				.build();
	}
	
	@GET
	@Path("/{id}")
	public Response getVisitbId(@BeanParam VisitBeanParam visitparam) {
		Visit visit = vc.getbyId(visitparam.getId());
		
		if(visit == null) {
			throw new AppException("Data not found", 404);
		}
		
		return Response.status(200)
				.entity(visit)
				.build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createVisit(VisitForm visitform) {
		Doctor doctor = dc.getbyId(visitform.getDoctor_id());
		Patient patient = pc.getbyId(visitform.getPatient_id());
		Visit visit = new Visit(); 
		
		if(patient == null) {
			throw new AppException("Patent not found", 404);
		}
		
		if(doctor == null) {
			throw new AppException("Doctor not found", 404);
		}
		
		visit.setDate(visitform.getDate());
		visit.setDoctor(doctor);
		visit.setPatient(patient);
		
		visit = vc.create(visit);
		
		return Response.status(201)
					.entity(visit)
					.build();
		
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateVisit(VisitForm visitform, @BeanParam VisitBeanParam visitparam) {
		
		Doctor doctor = dc.getbyId(visitform.getDoctor_id());
		Patient patient = pc.getbyId(visitform.getPatient_id());
		Visit visit = new Visit(); 
		
		if(patient == null) {
			throw new AppException("Patent not found", 404);
		}
		
		if(doctor == null) {
			throw new AppException("Doctor not found", 404);
		}
		
		visit.setDate(visitform.getDate());
		visit.setDoctor(doctor);
		visit.setPatient(patient);
		visit = vc.update(visit);
		
		return Response.status(200)
				.entity(visit)
				.build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response deleteVisit(@BeanParam VisitBeanParam visitparam) {
		Visit visit= vc.getbyId(visitparam.getId());
		
		if(visit == null) {
			throw new AppException("Data not found", 404);
		}
		
		vc.delete(visit);
		
		return Response.ok()
			       .build();
	}
	
	
}
