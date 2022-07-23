package resource;

import java.util.List;

import controller.DoctorController;
import entity.Doctor;
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
import resource.bean.DoctorBeanParam;
import resource.exception.AppException;

@Path("doctor")
@Produces( value= { MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Consumes(MediaType.APPLICATION_JSON)
public class DoctorResource {
	
	private DoctorController dc = new DoctorController();

	@GET
	@Path("/test")
	public Response test() {
		String result = dc.test();
		if(result != "") {
			throw new AppException("Exception work perfectly", 404);
		}
		
		return Response.ok()
			       .entity(result)
			       .build();
	}
	
	@GET
	public Response getDoctors() {
		List<Doctor> doctors= dc.getAll();
		Status status = Status.NO_CONTENT;
		
		if(doctors.size() > 0) {
			status = Status.OK;
		}
		
		return Response.status(status)
			       .entity(doctors)
			       .build();
	}
	
	@GET
	@Path("/{id}")
	public Response getDoctorbyId(@BeanParam DoctorBeanParam doctorfilter) {
		Doctor doctor= dc.getbyId(doctorfilter.getId());
		
		if(doctor == null) {
			throw new AppException("Data Not Found", 404);
		}
		
		return Response.ok()
			       .entity(doctor)
			       .build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createDoctor(Doctor doctor) {
		doctor = dc.create(doctor);
		
		
		return Response.status(201)
			       .entity(doctor)
			       .build();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateDoctor(Doctor docbody, @BeanParam DoctorBeanParam doctorfilter) {
		Doctor doctor= dc.getbyId(doctorfilter.getId());
		
		
		if(doctor == null) {
			throw new AppException("Data Not Found", 404);
		}
		
		System.out.println(doctor.getId());
		doctor.setCode(docbody.getCode());
		doctor.setFirstname(docbody.getFirstname());
		doctor.setLastname(docbody.getLastname());
		doctor.setGrade(docbody.getGrade());
		
		doctor = dc.update(doctorfilter.getId(), doctor);
		
		return Response.ok()
			       .entity(doctor)
			       .build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response deleteDoctor(@BeanParam DoctorBeanParam doctorfilter) {
		Doctor doctor= dc.getbyId(doctorfilter.getId());
		
		if(doctor == null) {
			throw new AppException("Data Not Found", 404);
		}
		
		dc.delete(doctor);
		
		return Response.ok()
			       .build();
	}
}
