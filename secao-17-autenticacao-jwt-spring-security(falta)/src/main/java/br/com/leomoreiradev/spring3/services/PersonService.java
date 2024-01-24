package br.com.leomoreiradev.spring3.services;

import br.com.leomoreiradev.spring3.dto.PersonDTO;
import br.com.leomoreiradev.spring3.exceptions.ResourceNotFoundException;
import br.com.leomoreiradev.spring3.mapper.DozerMapper;
import br.com.leomoreiradev.spring3.model.Person;
import br.com.leomoreiradev.spring3.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;
    private Logger logger = Logger.getLogger(PersonService.class.getName());


    public PersonDTO findById(Long id) {
        logger.info("Finding one person");
        var entity = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        return DozerMapper.parseObject(entity, PersonDTO.class);
    }


    public List<PersonDTO> findAll() {
        logger.info("Finding all people");
        return DozerMapper.parseListObjects(personRepository.findAll(), PersonDTO.class);
    }


    public PersonDTO create(PersonDTO personDTO) {
        logger.info("Creating one person");
        var entity = DozerMapper.parseObject(personDTO, Person.class);
        personDTO = DozerMapper.parseObject(personRepository.save(entity), PersonDTO.class);
        return personDTO;
    }

    public PersonDTO update(PersonDTO personDTO) {
        logger.info("Updating one person");
        var entity = personRepository.findById(personDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        entity.setFirstName(personDTO.getFirstName());
        entity.setLastName(personDTO.getLastName());
        entity.setAddress(personDTO.getAddress());
        entity.setGender(personDTO.getGender());

        personDTO = DozerMapper.parseObject(personRepository.save(entity), PersonDTO.class);
        return personDTO;
    }

    public void delete(Long id) {
        logger.info("Deleting one person");
        var entity = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        personRepository.delete(entity);
    }
}
