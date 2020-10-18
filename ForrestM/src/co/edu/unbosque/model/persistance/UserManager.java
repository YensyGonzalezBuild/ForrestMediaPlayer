package co.edu.unbosque.model.persistance;

import co.edu.unbosque.model.User;

public class UserManager {
	
	public UserManager() {
		
	}
	
	public boolean createUser() {
		return true;
	}
	
	public User findUser() {
		return new User();
	}
	
	public boolean userSignIn() {
		return true;
	}

}
