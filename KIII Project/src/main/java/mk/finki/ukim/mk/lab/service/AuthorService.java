package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.repository.jpa.AuthorRepositoryJpa;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface AuthorService  {
    List<Author> listAuthors();
    Author findById(Long id);
    Optional<Author> save(Long id, String name, String surname);
    Optional<Author> save(Author author);
}
