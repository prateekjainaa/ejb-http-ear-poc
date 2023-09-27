package com.ericsson.enm.rs;

import java.util.Hashtable;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.ericsson.remote.ejb.level1.api.PodNameReturner;

public class EJBCallByService {
	
	//@EJB
	PodNameReturner podNameReturner;
	
	String callingEJBLevel1() {
		/*
		 * System.out.println("Inside EJBCallByService.callingEJBLevel1"); return
		 * podNameReturner.returnPodName();
		 */
		final Hashtable<String, String> jndiProperties = new Hashtable<>();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        jndiProperties.put(Context.PROVIDER_URL, "http://localhost:8180/wildfly-services");

        try {
        	final Context context = new InitialContext(jndiProperties);
			podNameReturner = (PodNameReturner) context.lookup("ejb:poc-ear/remote-ejb/Level1Ejb!com.ericsson.remote.ejb.level1.api.PodNameReturner");
			if (null != podNameReturner) {
				System.out.println("EJB1 found");
			}			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return podNameReturner.returnPodName();		
	}

}
