package br.com.leomoreiradev.spring3.controllers;

import br.com.leomoreiradev.spring3.dto.BookDTO;
import br.com.leomoreiradev.spring3.services.BookService;
import br.com.leomoreiradev.spring3.util.Mediatype;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping(value = "/api/book/v1")
@Tag(name = "Book", description = "Endpoints for Managing Book")
public class BookController {


    @Autowired
    private BookService bookService;

    @GetMapping(produces = {Mediatype.APPLICATION_JSON,
            Mediatype.APPLICATION_XML,
            Mediatype.APPLICATION_YML
    })
    @Operation(
            summary = "Finds all Book",
            description = "Finds all Book",
            tags = {"Book"},
            responses = {
                    @ApiResponse(description = "Success",
                                 responseCode = "200",
                                 content = {
                                        @Content(mediaType = "application/json",
                                         array = @ArraySchema(schema = @Schema(implementation = BookDTO.class))
                                        )
                                 }
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public List<BookDTO> findAll() {
        return bookService.findAll();
    }

    @GetMapping(value = "/{id}", produces = {Mediatype.APPLICATION_JSON,
            Mediatype.APPLICATION_XML,
            Mediatype.APPLICATION_YML
    })
    @Operation(
            summary = "Finds a Book",
            description = "Finds a Book",
            tags = {"Book"},
            responses = {
                    @ApiResponse(description = "Success",
                                 responseCode = "200",
                                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookDTO.class))

                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public BookDTO findById(@PathVariable(value = "id") Long id) {
        return bookService.findById(id);
    }

    @PostMapping(produces = {Mediatype.APPLICATION_JSON,
            Mediatype.APPLICATION_XML,
            Mediatype.APPLICATION_YML},
            consumes = {Mediatype.APPLICATION_JSON,
                    Mediatype.APPLICATION_XML,
                    Mediatype.APPLICATION_YML}
    )
    @Operation(
            summary = "Adds a new Book",
            description = "Adds a new Book by passing in a JSON, XML or YML representation of the book",
            tags = {"Book"},
            responses = {
                    @ApiResponse(description = "Success",
                                 responseCode = "201",
                                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookDTO.class))

                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public BookDTO create(@RequestBody BookDTO book) {
        return bookService.create(book);
    }

    @PutMapping(produces = {Mediatype.APPLICATION_JSON,
            Mediatype.APPLICATION_XML,
            Mediatype.APPLICATION_YML},
            consumes = {Mediatype.APPLICATION_JSON,
                    Mediatype.APPLICATION_XML,
                    Mediatype.APPLICATION_YML})
    @Operation(
            summary = "Updates a Book",
            description = "Updated a Book by passing in a JSON, XML or YML representation of the book",
            tags = {"Book"},
            responses = {
                    @ApiResponse(description = "Success",
                                 responseCode = "200",
                                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookDTO.class))

                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public BookDTO update(@RequestBody BookDTO book) {
        return bookService.update(book);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(
            summary = "Deletes a Book",
            description = "Deletes a Book by passing in a JSON, XML or YML representation of the book",
            tags = {"Book"},
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

        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
