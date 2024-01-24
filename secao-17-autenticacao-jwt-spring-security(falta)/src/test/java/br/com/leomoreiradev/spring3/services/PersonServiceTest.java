package br.com.leomoreiradev.spring3.services;

import br.com.leomoreiradev.spring3.dto.PersonDTO;
import br.com.leomoreiradev.spring3.exceptions.ResourceNotFoundException;
import br.com.leomoreiradev.spring3.mapper.DozerMapper;
import br.com.leomoreiradev.spring3.model.Person;
import br.com.leomoreiradev.spring3.repositories.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;



    @Test
    void testFindById() {
        Long id = 1L;
        Person person = new Person();
        person.setId(id);
        when(personRepository.findById(id)).thenReturn(Optional.of(person));

        PersonDTO result = personService.findById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
    }

    @Test
    void testFindByIdNotFound() {
        Long id = 1L;
        when(personRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> personService.findById(id));
    }

    @Test
    void testFindAll() {
        Long id = 1L;
        Person person = new Person();
        person.setId(id);
        when(personRepository.findAll()).thenReturn(Arrays.asList(person));


        List<PersonDTO> result = personService.findAll();

        assertNotNull(result);
        assertTrue(!result.isEmpty());
    }

    @Test
    void testCreate() {
        PersonDTO personDTO = new PersonDTO();
        Person entity = DozerMapper.parseObject(personDTO, Person.class);
        when(personRepository.save(any())).thenReturn(entity);

        PersonDTO result = personService.create(personDTO);

        assertNotNull(result);
    }

    @Test
    void testUpdate() {
        Long id = 1L;
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(id);
        Person entity = new Person();
        entity.setId(id);
        when(personRepository.findById(id)).thenReturn(Optional.of(entity));
        when(personRepository.save(any())).thenReturn(entity);

        PersonDTO result = personService.update(personDTO);

        assertNotNull(result);
        assertEquals(id, result.getId());
    }

    @Test
    void testUpdateNotFound() {
        Long id = 1L;
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(id);
        when(personRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> personService.update(personDTO));
    }

    @Test
    void testDelete() {
        Long id = 1L;
        Person entity = new Person();
        entity.setId(id);
        when(personRepository.findById(id)).thenReturn(Optional.of(entity));

        personService.delete(id);

        verify(personRepository, times(1)).delete(entity);
    }

    @Test
    void testDeleteNotFound() {
        Long id = 1L;
        when(personRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> personService.delete(id));
    }
}
