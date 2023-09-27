package com.poc.enm.ejb.remote;

import java.util.Hashtable;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jboss.ejb3.annotation.SecurityDomain;

import com.ericsson.remote.ejb.level1.api.PodNameReturner;

@Stateless
@Remote(PodNameReturner.class)
public class Level1Ejb implements PodNameReturner {

	com.poc.remote.ejb.level2.api.PodNameReturner podNameReturner;
	
	@Override
	public String returnPodName() {
		System.out.println("Inside Level1Ejb, returning value of POD_NAME");
		try {
			InitialContext ic = getContext();
			podNameReturner = (com.poc.remote.ejb.level2.api.PodNameReturner) 
					lookup(ic, "ejb:poc-ear2/remote-ejb2/Level2Ejb!com.poc.remote.ejb.level2.api.PodNameReturner");
			if (null != podNameReturner) {
				System.out.println("level2 ejb found. ");
			} else {
				System.out.println("level2 ejb not found.");
			}
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			System.err.println("Error occured while looking up.");
			e.printStackTrace();
		}
		//return "ejb level1: " + System.getenv("POD_NAME");
		return System.getenv("POD_NAME") + " , level2 pod name: "+podNameReturner.returnPodName();
	}

    private static InitialContext getContext() throws NamingException {
    	final Hashtable<String, String> jndiProperties = new Hashtable<>();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        jndiProperties.put(Context.PROVIDER_URL, "http://localhost:8080/wildfly-services");
        //jndiProperties.put(Context.SECURITY_CREDENTIALS, "quickstartUser");
        //jndiProperties.put(Context.SECURITY_PRINCIPAL, "quickstartPwd1!");
        return new InitialContext(jndiProperties);
    }

    private Object lookup(InitialContext ic, String name) {
        try {
            Object proxy = ic.lookup(name);
            if (proxy == null) {
                System.out.println("lookup(" + name + ") returns no proxy object");
            }
            return proxy;
        } catch (NamingException e) {
            System.out.println("Failed to lookup(" + name + ")");
            return null;
        }
    }
}
