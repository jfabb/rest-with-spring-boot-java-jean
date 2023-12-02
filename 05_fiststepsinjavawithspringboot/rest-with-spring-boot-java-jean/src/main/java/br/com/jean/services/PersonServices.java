package br.com.jean.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jean.exceptions.ResourceNotFoundException;
import br.com.jean.model.Person;
import br.com.jean.repositories.PersonREpository;


@Service
public class PersonServices {

	
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	@Autowired
	PersonREpository repository;
	
	public List<Person> finAll() {
		
		
		
		return repository.findAll();
		
	}
	
	
	public Person findById(Long id) {
		
		logger.info("Fidding one Person !");
		
		
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person not found with id: " + id));
		
	}
	
	public Person createPerson(Person person) {
		
		logger.info("Creating one  Person !");
		
		
		return repository.save(person);
	}
	
	public void deletePerson(Long id) {
		
		logger.info("Deleting one  Person !");

		 Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person not found with id: " + id));
		 
	    repository.delete(entity);
	    
	}
	
	public Person updatePerson(Person person) {
		
		logger.info("Update one  Person !");
		
		 Person entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("Person not found with id: " + person.getId()));
		
		 entity.setFirstName(person.getFirstName());
		 entity.setLastName(person.getLastName());
		 entity.setAdress(person.getAdress());
		 entity.setGender(person.getGender());
		 
		return repository.save(entity);
	}
	
	
	
	
}
