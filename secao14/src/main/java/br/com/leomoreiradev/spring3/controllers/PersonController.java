package br.com.leomoreiradev.spring3.controllers;

import br.com.leomoreiradev.spring3.dto.PersonDTO;
import br.com.leomoreiradev.spring3.services.PersonService;
import br.com.leomoreiradev.spring3.util.Mediatype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/person")
public class PersonController {


    @Autowired
    private PersonService personService;

    @GetMapping(value = "/{id}", produces = {Mediatype.APPLICATION_JSON,
            Mediatype.APPLICATION_XML,
            Mediatype.APPLICATION_YML
    })
    public PersonDTO findById(@PathVariable(value = "id") Long id) {
        return personService.findById(id);
    }

    @GetMapping(produces = {Mediatype.APPLICATION_JSON,
            Mediatype.APPLICATION_XML,
            Mediatype.APPLICATION_YML
    })
    public List<PersonDTO> findAll() {
        return personService.findAll();
    }

    @PostMapping(produces = {Mediatype.APPLICATION_JSON,
            Mediatype.APPLICATION_XML,
            Mediatype.APPLICATION_YML},
            consumes = {Mediatype.APPLICATION_JSON,
                    Mediatype.APPLICATION_XML,
                    Mediatype.APPLICATION_YML}
    )
    public PersonDTO create(@RequestBody PersonDTO person) {
        return personService.create(person);
    }

    @PutMapping(produces = {Mediatype.APPLICATION_JSON,
            Mediatype.APPLICATION_XML,
            Mediatype.APPLICATION_YML},
            consumes = {Mediatype.APPLICATION_JSON,
                    Mediatype.APPLICATION_XML,
                    Mediatype.APPLICATION_YML})
    public PersonDTO update(@RequestBody PersonDTO person) {
        return personService.update(person);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) {

        personService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
