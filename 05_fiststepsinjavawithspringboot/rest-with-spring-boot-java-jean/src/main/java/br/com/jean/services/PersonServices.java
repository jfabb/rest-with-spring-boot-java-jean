package br.com.jean.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.jean.model.Person;


@Service
public class PersonServices {

	private final AtomicLong counter = new AtomicLong();
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	public List<Person> finAll() {
		
		logger.info("Fidding all People !");
		
		List<Person> persons = new ArrayList<>();
		
		for (int i = 0; i<8 ;i++) {
			
			Person person = mockPerson(i);
			persons.add(person);
		}
		
		return persons;
		
	}
	
	
	public Person findById(String id) {
		
		logger.info("Fidding one Person !");
		
		Person person = new Person();
		
		person.setId(counter.incrementAndGet());
		person.setFirstName("Andreza");
		person.setLastName("Gomes");
		person.setAdress("Rua São Paulo, Bairro dos Estados,João Pessoa, PB");
		person.setGender("female");
		
		return person;
	}
	
	public Person createPerson(Person person) {
		
		logger.info("Creating one  Person !");
		
		
		return person;
	}
	public void deletePerson(String id) {
		
		logger.info("Deleting one  Person !");
		
	}
	
	public Person updatePerson(Person person) {
		
		logger.info("Update one  Person !");
		
		return person;
	}
	
	
	private Person mockPerson(int i) {
		
		Person person = new Person();
		
		person.setId(counter.incrementAndGet());
		person.setFirstName(" Name: " + i);
		person.setLastName("LastName: " + i);
		person.setAdress("Adress PE-BR : " + i);
		person.setGender("male");
		
		return person;
	}
	
}
