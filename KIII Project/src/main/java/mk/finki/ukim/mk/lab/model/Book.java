package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String genre;
    private Integer year;
    @ManyToMany
    private List<Author> bookAuthors;

    private String isbn;
    @ManyToOne
    private BookStore bookStore;
    public Book(String isbn, String title, String genre, int year, BookStore bookStore) {
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.bookAuthors = new ArrayList<>();
        this.id=(long) (Math.random()*1000);
        this.bookStore = bookStore;


    }

    public Book(String isbn, String title, String genre, int year) {
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.year = year;



    }

    public Book() {
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }

    public List<Author> getAuthors() {
        return bookAuthors;
    }

    public String getIsbn() {
        return isbn;
    }


    public void setBookStore(BookStore bookStore) {
        this.bookStore=bookStore;
    }

    public Long getBookStoreId(){return this.bookStore.getId();}

    @OneToMany
    private List<Review> reviews;

    public List<Author> getBookAuthors() {
        return bookAuthors;
    }
    public String getBookStore(){
        return bookStore.getName();
    }

    public double getBookRating(){
        return reviews.stream().mapToDouble(Review::getScore).average().orElse(0.0);
    }
}
