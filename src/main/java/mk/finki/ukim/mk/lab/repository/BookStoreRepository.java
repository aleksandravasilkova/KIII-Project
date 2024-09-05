package mk.finki.ukim.mk.lab.repository;


import mk.finki.ukim.mk.lab.model.BookStore;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class BookStoreRepository {
    private final List<BookStore> bookStores;

    public BookStoreRepository() {
        this.bookStores = new ArrayList<>();
        this.bookStores.add(new BookStore( "BookStore 1", "Skopje", "street Macedonia 1"));
        this.bookStores.add(new BookStore("BookStore 2", "Prilep", "street Macedonia 2"));
        this.bookStores.add(new BookStore( "BookStore 3", "Skopje", "street Macedonia 3"));
        this.bookStores.add(new BookStore( "BookStore 4", "Bitola", "street Macedonia 4"));
        this.bookStores.add(new BookStore( "BookStore 5", "Probistip", "street Macedonia 5"));
    }

    public List<BookStore> findAll() {
        return this.bookStores;
    }
}

