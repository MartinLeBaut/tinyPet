package tinyPet;


import java.util.ArrayList;
import java.util.List;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.SortDirection;


@Api(name = "petApi",
version = "v1")
public class PetitionEndpoint {
	@ApiMethod(name = "addPetition")
	public Entity addPetition(@Named("petName") String petName, @Named("sender") String sender) {
		
		List<String> signatories= new ArrayList<>();
		signatories.add(sender);
		
		Entity pet = new Entity("Petition", petName);
		pet.setProperty("petName", petName);
		pet.setProperty("sender", sender);
		pet.setProperty("nbSignature", 1);
		pet.setProperty("signatories", signatories);
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		datastore.put(pet);
		return  pet;
	}
	
	@ApiMethod(name = "listTopPetition")
	public List<Entity> listTopPetition(){
		Query q = new Query("Petition").addSort("nbSignature", SortDirection.DESCENDING );
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		PreparedQuery pq = datastore.prepare(q);
		List<Entity> listeTopPet = pq.asList(FetchOptions.Builder.withLimit(100));
		return listeTopPet;	
	}
	
	@ApiMethod(name = "searchPetition")
	public List<Entity> searchPetition(@Named("sender") String sender){
		Query q = new Query("Petition").setFilter(new FilterPredicate("sender", FilterOperator.EQUAL, sender));
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		PreparedQuery pq = datastore.prepare(q);
		List<Entity> listePet = pq.asList(FetchOptions.Builder.withDefaults());
		return listePet;
	}
	
	@ApiMethod(name = "signPet")
	public Entity signPet(@Named("petId") long petId, @Named("userName") String userName) throws EntityNotFoundException {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Key keyPet = KeyFactory.createKey("Petition", petId);
		Entity petition = datastore.get(keyPet);
		List signatories = (ArrayList<String>) petition.getProperty("signatories");
		signatories.add(userName);
		petition.setProperty("signatories", signatories);
		petition.setProperty("nbSignature", signatories.size());

		datastore.put(petition);
		return petition;
	}

}