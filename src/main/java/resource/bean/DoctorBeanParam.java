package resource.bean;

import jakarta.ws.rs.PathParam;

public class DoctorBeanParam {
	private @PathParam("id") Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
