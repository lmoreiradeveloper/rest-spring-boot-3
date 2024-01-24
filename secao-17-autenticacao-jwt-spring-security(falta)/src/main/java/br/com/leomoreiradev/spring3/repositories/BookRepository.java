package br.com.leomoreiradev.spring3.repositories;

import br.com.leomoreiradev.spring3.model.Book;
import br.com.leomoreiradev.spring3.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
