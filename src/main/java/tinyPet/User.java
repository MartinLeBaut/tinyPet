package tinyPet;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable(identityType=IdentityType.APPLICATION)
public class User {
	@PrimaryKey
	@Persistent
	Key userId;
	@Persistent
	String userName;
	@Persistent
	String userLogin;
	@Persistent
	String userPassword;
	@Persistent 
	String userMail;
	
	public Key getKey() {
		return this.userId;
	}
	
	public void setKey(Key id) {
		this.userId = id;
	}
	
	public String getName() {
		return this.userName;
	}

	public void setName(String name) {
		this.userName = name;
	}
	
	public String getLogin() {
		return this.userLogin;
	}
	
	public void setLogin(String login) {
		this.userLogin = login;
	}
	
	public String getPassword() {
		return userPassword;
	}

	public void setPassword(String password) {
		this.userPassword = password;
	}
	
	public String getMail() {
		return this.userMail;
	}
	
	public void setMail(String mail) {
		this.userMail = mail;
	}



}
