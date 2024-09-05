package mk.finki.ukim.mk.lab.service.impls;

import mk.finki.ukim.mk.lab.model.BookStore;
import mk.finki.ukim.mk.lab.repository.BookStoreRepository;
import mk.finki.ukim.mk.lab.repository.jpa.BookStoreRepositoryJpa;
import mk.finki.ukim.mk.lab.service.BookStoreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookStoreServiceImpl implements BookStoreService {

    private final BookStoreRepositoryJpa bookStoreRepository;

    public BookStoreServiceImpl(BookStoreRepositoryJpa bookStoreRepository) {
        this.bookStoreRepository = bookStoreRepository;
    }

    @Override
    public List<BookStore> findAll() {
        return bookStoreRepository.findAll();
    }



    @Override
    public Optional<BookStore> save(String name, String country, String address) {
        return Optional.of(bookStoreRepository.save(new BookStore(name, country, address)));
    }
}