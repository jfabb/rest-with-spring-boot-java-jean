package br.com.jean.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jean.data.vo.v1.PersonVO;
import br.com.jean.exceptions.ResourceNotFoundException;
import br.com.jean.mapper.DozerMapper;
import br.com.jean.model.Person;
import br.com.jean.repositories.PersonREpository;


@Service
public class PersonServices {

	
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	@Autowired
	PersonREpository repository;
	
	public List<PersonVO> finAll() {
		
		return DozerMapper.parseListObjects(repository.findAll(),PersonVO.class);
		
	}
	
	
	public PersonVO findById(Long id) {
		
		logger.info("Fidding one Person !");
		
		
		Person person =  repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person not found with id: " + id));
		
		return DozerMapper.parseObject(person,PersonVO.class);
	}
	
	public PersonVO createPerson(PersonVO personVO) {
		
		logger.info("Creating one  Person !");
		
		Person entity =  DozerMapper.parseObject(personVO,Person.class);
		
		return DozerMapper.parseObject(repository.save(entity),PersonVO.class);
	}
	
	public void deletePerson(Long id) {
		
		logger.info("Deleting one  Person !");

		 Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person not found with id: " + id));
		 
	    repository.delete(entity);
	    
	}
	
	public PersonVO updatePerson(PersonVO person) {
		
		logger.info("Update one  Person !");
		
		 Person entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("Person not found with id: " + person.getId()));
		
		 entity.setFirstName(person.getFirstName());
		 entity.setLastName(person.getLastName());
		 entity.setAdress(person.getAdress());
		 entity.setGender(person.getGender());
		 
		 return DozerMapper.parseObject(repository.save(entity),PersonVO.class);
	}
	
	
	
	
}
