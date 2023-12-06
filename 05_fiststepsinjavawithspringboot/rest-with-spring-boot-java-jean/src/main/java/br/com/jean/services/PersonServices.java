package br.com.jean.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import br.com.jean.PersonController;
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
		
		var persons =  DozerMapper.parseListObjects(repository.findAll(),PersonVO.class);
		
		persons.stream().forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
		
		return persons;
	}
	
	
	public PersonVO findById(Long id) {
		
		logger.info("Fidding one Person !");
		
		
		Person person =  repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person not found with id: " + id));
		
		PersonVO vo =  DozerMapper.parseObject(person,PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		
		return vo; 
	}
	
	public PersonVO createPerson(PersonVO personVO) {
		
		logger.info("Creating one  Person !");
		
		Person entity =  DozerMapper.parseObject(personVO,Person.class);
		
		PersonVO vo= DozerMapper.parseObject(repository.save(entity),PersonVO.class);
		 
		 vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		 
		 return vo;
	}
	
	public void deletePerson(Long id) {
		
		logger.info("Deleting one  Person !");

		 Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person not found with id: " + id));
		 
	    repository.delete(entity);
	    
	}
	
	public PersonVO updatePerson(PersonVO person) {
		
		logger.info("Update one  Person !");
		
		 Person entity = repository.findById(person.getKey()).orElseThrow(() -> new ResourceNotFoundException("Person not found with id: " + person.getKey()));
		
		 entity.setFirstName(person.getFirstName());
		 entity.setLastName(person.getLastName());
		 entity.setAdress(person.getAdress());
		 entity.setGender(person.getGender());
		 
		 PersonVO vo= DozerMapper.parseObject(repository.save(entity),PersonVO.class);
		 
		 vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		 
		 return vo;
	}
	
	
	
	
}
