package com.insta.taskmanager9000.business;

import java.io.Serializable;

public class Contributor implements Serializable {
	
	private static final long serialVersionUID = -3278841120040194379L;
	private String name;
	private String mail;
	private String status;
	
	public Contributor(String name, String mail){
		this.name = name;
		this.mail = mail;
		this.status = "";
	}
	
	public Contributor(String name, String mail, String status){
		this.name = name;
		this.mail = mail;
		this.status = status;
	}	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String toString(){
		return this.name + " (" + this.mail + ") " + this.status;
	}

}
