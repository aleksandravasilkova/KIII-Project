package mk.finki.ukim.mk.lab.service.impls;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;
import mk.finki.ukim.mk.lab.repository.jpa.BookRepositoryJpa;
import mk.finki.ukim.mk.lab.service.BookService;
import org.springframework.stereotype.Service;
import mk.finki.ukim.mk.lab.repository.AuthorRepository;
import mk.finki.ukim.mk.lab.repository.BookRepository;
import mk.finki.ukim.mk.lab.service.AuthorService;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{
    private final BookRepositoryJpa bookRepository;
    private final AuthorRepository authorRepository;
    public BookServiceImpl(BookRepositoryJpa bookRepository, AuthorRepository authorRepository){
        this.bookRepository = bookRepository;
        this.authorRepository= authorRepository;
    }


    @Override
    public List<Book> listBooks() {
        return bookRepository.findAll();
    }


    @Override
    public Author addAuthorToBook(Long authorId, String isbn) {
        Book book = bookRepository.findByIsbn(isbn);
        if(book!=null){
            Author author = authorRepository.findById(authorId).orElse(null);
                    if(author!=null){
                        book.getAuthors().add(author);


                    }
        }
return null;
    }

    @Override
    public Book findBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }
    @Override
    public double getBookRating(Book book) {
        return book.getBookRating();
    }

    @Override
    public Optional<Book> save(String isbn, String title, String genre, int year, BookStore bookStore) {
        return Optional.of(bookRepository.save(new Book(isbn, title, genre, year, bookStore)));
    }

    @Override
    public Optional<Book> save(Book book) {
        return Optional.of(bookRepository.save(book));
    }

    @Override
    public void DeleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
