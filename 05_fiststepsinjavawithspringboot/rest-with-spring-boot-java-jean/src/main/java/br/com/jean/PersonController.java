package br.com.jean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jean.data.vo.v1.PersonVO;
import br.com.jean.services.PersonServices;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

	@Autowired
	private PersonServices service;

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public PersonVO findById(@PathVariable (value = "id")Long id) throws NumberFormatException {
		

     return service.findById(id);
    		 
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable (value = "id")Long id)  {
		
		service.deletePerson(id);
		return ResponseEntity.noContent().build();
		
	}
	
	@PostMapping( consumes =  MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PersonVO create(@RequestBody PersonVO person)  {
		
		return service.createPerson(person);
		
	}
	
	@PutMapping( consumes =  MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public br.com.jean.data.vo.v1.PersonVO update(@RequestBody PersonVO person)  {
		
		return service.updatePerson(person);
		
	}
	
	
	@GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PersonVO> findById()  {
		
		return service.finAll();
		
	}

}
