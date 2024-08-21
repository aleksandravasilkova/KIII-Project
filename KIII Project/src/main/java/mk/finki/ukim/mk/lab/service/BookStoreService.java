package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.BookStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface BookStoreService {
    List<BookStore> findAll();
    Optional<BookStore> save(String name, String country, String address);

}