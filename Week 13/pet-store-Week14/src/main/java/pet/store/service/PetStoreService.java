package pet.store.service;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pet.store.controller.model.PetStoreData;
import pet.store.dao.PetStoreDao;
import pet.store.entity.PetStore;

//this is where the database transactions take place
@Service
public class PetStoreService {
	
	@Autowired
	private PetStoreDao petStoreDao;

	
	@Transactional(readOnly = false)
	public PetStoreData savePetStore(PetStoreData petStoreData) {
		PetStore petStore = findOrCreatePetStore(petStoreData.getPetStoreId());
		
		copyPetStoreFields(petStore, petStoreData);
		PetStore savedPetStore = petStoreDao.save(petStore);
		return convertToPetStoreData(savedPetStore);
		
		
	}

	//convert a DTO to a petStore object
	private void copyPetStoreFields(PetStore petStore, PetStoreData petStoreData) {
		petStore.setPetStoreId(petStoreData.getPetStoreId());
	    petStore.setPetStoreName(petStoreData.getPetStoreName());
	    petStore.setPetStoreAddress(petStoreData.getPetStoreAddress());
	    petStore.setPetStoreCity(petStoreData.getPetStoreCity());
	    petStore.setPetStoreState(petStoreData.getPetStoreState());
	    petStore.setPetStoreZip(petStoreData.getPetStoreZip());
	    petStore.setPetStorePhone(petStoreData.getPetStorePhone());
	}

	//decided to find or create a new pet store based on Id
	private PetStore findOrCreatePetStore(Long petStoreId) {
        if (Objects.isNull(petStoreId)) {
            return new PetStore();
        } else {
            return findPetStoreById(petStoreId);
        }
    }
	//converts a petStore into A DTO format
	private PetStoreData convertToPetStoreData(PetStore petStore) {
	    PetStoreData petStoreData = new PetStoreData();
	    petStoreData.setPetStoreId(petStore.getPetStoreId());
	    petStoreData.setPetStoreName(petStore.getPetStoreName());
	    petStoreData.setPetStoreAddress(petStore.getPetStoreAddress());
	    petStoreData.setPetStoreCity(petStore.getPetStoreCity());
	    petStoreData.setPetStoreState(petStore.getPetStoreState());
	    petStoreData.setPetStoreZip(petStore.getPetStoreZip());
	    petStoreData.setPetStorePhone(petStore.getPetStorePhone());
	    return petStoreData;
	}

	private PetStore findPetStoreById(Long petStoreId) { //if there is no ID, then throw an exception saying its not found
		return petStoreDao.findById(petStoreId)
				.orElseThrow(() -> new NoSuchElementException("Pet store ID:" + petStoreId + " not found"));
	}

}
