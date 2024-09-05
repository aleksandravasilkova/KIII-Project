package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;
import org.springframework.stereotype.Repository;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Repository
public class BookRepository{
    List<Book> bookList;
    private BookStoreRepository bookStoreRepository;
    public BookRepository(BookStoreRepository bookStoreRepository) {
        this.bookStoreRepository=bookStoreRepository;
        Random random=new Random();
        bookList = new ArrayList<>();
        int size = bookStoreRepository.findAll().size();
        BookStore bookStore = bookStoreRepository.findAll().get(random.nextInt(size));
        bookList.add(new Book("11", "Survive", "Mistery", 1982, bookStore));
        bookStore=bookStoreRepository.findAll().get(random.nextInt(size));
        bookList.add(new Book("22", "Titanic 3", "Novel", 1999,bookStore));
        bookStore=bookStoreRepository.findAll().get(random.nextInt(size));
        bookList.add(new Book("33", "Palestra", "Thriler", 2002,bookStore));
        bookStore=bookStoreRepository.findAll().get(random.nextInt(size));
        bookList.add(new Book("44", "Desert", "Thriler", 2005,bookStore));
        bookStore=bookStoreRepository.findAll().get(random.nextInt(size));
        bookList.add(new Book("55", "Final move", "Romance", 1997,bookStore));
    }

    public List<Book> findAll(){
    return bookList;
    }

    public Book findByIsbn(String isbn){

        for(Book book : bookList)
        {
            if(book.getIsbn().equals(isbn)){
                return book;
            }
        }
        return null;

    }

    Author addAuthorToBook(Author author, Book book){
        book.getAuthors().add(author);
        return author;
    }




}

