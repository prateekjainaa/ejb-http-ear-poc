package com.poc.enm.ejb.remote.level2;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.jboss.ejb3.annotation.SecurityDomain;

import com.poc.remote.ejb.level2.api.PodNameReturner;



@Stateless
@Remote(PodNameReturner.class)
//@RolesAllowed({"guest"})
//@SecurityDomain("other")
@PermitAll
public class Level2Ejb implements PodNameReturner {

	@Override
	//@RolesAllowed("guest")
	public String returnPodName() {
		System.out.println("Inside Level2Ejb, returning value of POD_NAME");
		return System.getenv("POD_NAME");
	}

}
