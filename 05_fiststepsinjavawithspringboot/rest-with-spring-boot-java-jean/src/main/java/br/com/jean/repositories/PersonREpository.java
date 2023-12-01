package br.com.jean.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jean.model.Person;

@Repository
public interface PersonREpository extends JpaRepository<Person, Long> {}
