package com.mitu.contactbook;

public class contactHolder {

	 public int id;
	    public String name;
	    public String phone;

	    public contactHolder(int id, String name, String phone) {
	        this.id = id;
	        this.name = name;
	        this.phone = phone;
	    }

	    public contactHolder(String name, String phone) {
	        this.name = name;
	        this.phone = phone;
	    }
	
}
