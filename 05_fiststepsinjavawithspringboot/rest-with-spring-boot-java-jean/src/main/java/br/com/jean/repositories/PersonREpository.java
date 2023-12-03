package br.com.jean.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jean.model.Person;


public interface PersonREpository extends JpaRepository<Person, Long> {}
