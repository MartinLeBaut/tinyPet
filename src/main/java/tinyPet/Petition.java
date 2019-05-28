package tinyPet;

import java.util.List;

import com.google.appengine.api.datastore.Key;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType=IdentityType.APPLICATION)
public class Petition {
	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
	Key petId;
	
	@Persistent
	String petName;
	
	@Persistent
	String sender;
	
	@Persistent
	int nbSignature;
	
	@Persistent
	List<String> signatories;

	public void setId(Key petId) {
		this.petId = petId;
	}
	
	public Key getKey() {
		return this.petId;
	}
	
	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public int getNbSignature() {
		return nbSignature;
	}

	public void setNbSignature(int nbSignature) {
		this.nbSignature = nbSignature;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}
	
	public List<String> getSignatories(){
		return this.signatories;
	}
	
	public void setSignatories(List<String> signatories) {
		this.signatories = signatories;
	}
}
