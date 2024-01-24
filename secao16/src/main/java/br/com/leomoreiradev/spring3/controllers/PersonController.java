package br.com.leomoreiradev.spring3.controllers;

import br.com.leomoreiradev.spring3.dto.PersonDTO;
import br.com.leomoreiradev.spring3.services.PersonService;
import br.com.leomoreiradev.spring3.util.Mediatype;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//Escopo de controller
//@CrossOrigin(origins = "http://localhost:8080") // permitindo acesso apenas dessa origem
@RestController
@RequestMapping(value = "/api/person/v1")
@Tag(name = "People", description = "Endpoints for Managing People")
public class PersonController {


    @Autowired
    private PersonService personService;

    @GetMapping(produces = {Mediatype.APPLICATION_JSON,
            Mediatype.APPLICATION_XML,
            Mediatype.APPLICATION_YML
    })
    @Operation(
            summary = "Finds all People",
            description = "Finds all People",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success",
                                 responseCode = "200",
                                 content = {
                                        @Content(mediaType = "application/json",
                                         array = @ArraySchema(schema = @Schema(implementation = PersonDTO.class))
                                        )
                                 }
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public List<PersonDTO> findAll() {
        return personService.findAll();
    }

    //Escopo de metodo
    //@CrossOrigin(origins = "http://localhost:8080") // permitindo acesso apenas dessa origem
    @GetMapping(value = "/{id}", produces = {Mediatype.APPLICATION_JSON,
            Mediatype.APPLICATION_XML,
            Mediatype.APPLICATION_YML
    })
    @Operation(
            summary = "Finds a Person",
            description = "Finds a Person",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success",
                                 responseCode = "200",
                                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonDTO.class))

                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public PersonDTO findById(@PathVariable(value = "id") Long id) {
        return personService.findById(id);
    }

    //@CrossOrigin(origins = {"http://localhost:8080", "http://teste.com.br"})
    @PostMapping(produces = {Mediatype.APPLICATION_JSON,
            Mediatype.APPLICATION_XML,
            Mediatype.APPLICATION_YML},
            consumes = {Mediatype.APPLICATION_JSON,
                    Mediatype.APPLICATION_XML,
                    Mediatype.APPLICATION_YML}
    )
    @Operation(
            summary = "Adds a new Person",
            description = "Adds a new Person by passing in a JSON, XML or YML representation of the person",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success",
                                 responseCode = "201",
                                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonDTO.class))

                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
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
    @Operation(
            summary = "Updates a Person",
            description = "Updated a Person by passing in a JSON, XML or YML representation of the person",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success",
                                 responseCode = "200",
                                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonDTO.class))

                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public PersonDTO update(@RequestBody PersonDTO person) {
        return personService.update(person);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(
            summary = "Deletes a Person",
            description = "Deletes a Person by passing in a JSON, XML or YML representation of the person",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "No content",
                                 responseCode = "204",
                                 content = @Content

                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) {

        personService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
