package co.edu.unbosque.model;

import java.util.UUID;

public class User {
	
	private UUID id;
	
	private String Name;
	
	private String Document;
	
	private String DocumentType; 
	
	private String Role;
	
	private String Password;
	
	public User() {
		
	}

	public User(UUID id, String name, String document, String documentType, String role, String password) {

		this.id = id;
		this.Name = name;
		this.Document = document;
		this.DocumentType = documentType;
		this.Role = role;
		this.Password = password;
	}

	public UUID getId() {
		return this.id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return this.Name;
	}

	public void setName(String name) {
		this.Name = name;
	}

	public String getDocument() {
		return this.Document;
	}

	public void setDocument(String document) {
		this.Document = document;
	}

	public String getDocumentType() {
		return this.DocumentType;
	}

	public void setDocumentType(String documentType) {
		this.DocumentType = documentType;
	}

	public String getRole() {
		return this.Role;
	}

	public void setRole(String role) {
		this.Role = role;
	}

	public String getPassword() {
		return this.Password;
	}

	public void setPassword(String password) {
		this.Password = password;
	}
	
	
	

}
