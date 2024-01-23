package br.com.leomoreiradev.spring3.services;

import br.com.leomoreiradev.spring3.dto.BookDTO;
import br.com.leomoreiradev.spring3.exceptions.ResourceNotFoundException;
import br.com.leomoreiradev.spring3.mapper.DozerMapper;
import br.com.leomoreiradev.spring3.model.Book;
import br.com.leomoreiradev.spring3.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    private Logger logger = Logger.getLogger(BookService.class.getName());

    public BookDTO findById(Long id) {
        logger.info("Finding one book");
        var entity = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        return DozerMapper.parseObject(entity, BookDTO.class);
    }


    public List<BookDTO> findAll() {
        logger.info("Finding all books");
        return DozerMapper.parseListObjects(bookRepository.findAll(), BookDTO.class);
    }

    public BookDTO create(BookDTO bookDTO) {
        logger.info("Creating one book");
        var entity = DozerMapper.parseObject(bookDTO, Book.class);
        bookDTO = DozerMapper.parseObject(bookRepository.save(entity), BookDTO.class);
        return bookDTO;
    }

    public BookDTO update(BookDTO bookDTO) {
        logger.info("Updating one book");
        var entity = bookRepository.findById(bookDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        entity.setId(bookDTO.getId());
        entity.setAuthor(bookDTO.getAuthor());
        entity.setPrice(bookDTO.getPrice());
        entity.setPrice(bookDTO.getPrice());

        bookDTO = DozerMapper.parseObject(bookRepository.save(entity), BookDTO.class);
        return bookDTO;
    }

    public void delete(Long id) {
        logger.info("Deleting one book");
        var entity = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        bookRepository.delete(entity);
    }
}
