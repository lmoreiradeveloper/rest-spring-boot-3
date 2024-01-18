package br.com.leomoreiradev.spring3.services;

import br.com.leomoreiradev.spring3.exceptions.ResourceNotFoundException;
import br.com.leomoreiradev.spring3.model.Person;
import br.com.leomoreiradev.spring3.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public Person findById(Long id) {
        logger.info("Finding one person");
        return personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
    }


    public List<Person> findAll() {
        logger.info("Finding all people");
        return personRepository.findAll();
    }

    public Person create(Person person) {
        logger.info("Creating one person");
        return personRepository.save(person);
    }

    public Person update(Person person) {
        logger.info("Updating one person");
        var entity = personRepository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return personRepository.save(entity);
    }

    public void delete(Long id) {
        logger.info("Deleting one person");
        var entity = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        personRepository.delete(entity);
    }
}
