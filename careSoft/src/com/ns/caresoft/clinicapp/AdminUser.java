package com.ns.caresoft.clinicapp;

import java.util.ArrayList;
import java.util.Date;

public class AdminUser extends User implements HIPAACompliantUser, HIPAACompliantAdmin  {
	private Integer employeeID;
    private String role;
    private ArrayList<String> securityIncidents = new ArrayList<String>();

	public AdminUser(Integer id, String role) {
		super(id);
		this.role = role;
	}

	@Override
	public boolean assignPin(int pin) {
		String pinStr = String.valueOf(pin);
		if(pinStr.length() < 6) {
			return false;
		}
		return true;
	}

	@Override
	public boolean accessAuthorized(Integer confirmedAuthID) {
		if(this.id != confirmedAuthID) {
			authIncident();
			return false;
		}
		return true;
	}
	
	public void newIncident(String notes) {
        String report = String.format(
            "Datetime Submitted: %s \n,  Reported By ID: %s\n Notes: %s \n", 
            new Date(), this.id, notes
        );
        securityIncidents.add(report);
    }
    public void authIncident() {
        String report = String.format(
            "Datetime Submitted: %s \n,  ID: %s\n Notes: %s \n", 
            new Date(), this.id, "AUTHORIZATION ATTEMPT FAILED FOR THIS USER"
        ); 
        this.securityIncidents.add(report);
    }

	@Override
	public ArrayList<String> reportSecurityIncidents() {
		// TODO Auto-generated method stub
		return this.securityIncidents;
	}
	
	// getter and setter

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
