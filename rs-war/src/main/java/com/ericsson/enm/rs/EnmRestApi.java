package com.ericsson.enm.rs;

import javax.ejb.EJB;
import javax.inject.Inject;
//import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.ericsson.remote.ejb.level1.api.PodNameReturner;

@Path("/ejb-http-poc")
public class EnmRestApi {

	@EJB
	PodNameReturner podNameReturner;
	
	//@Inject
	//EJBCallByService ejbCallByService;

    @GET
    @Path("/json")
    @Produces({ "application/json" })
    public String getEjbCallPath() {
    	System.out.println("request received.");
        return "{\"result\":\"" + podNameReturner.returnPodName() + "\"}";
    }

}
