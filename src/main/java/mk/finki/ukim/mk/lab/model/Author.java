package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Data
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String biography;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;

    @Convert(converter = AuthorFullNameConverter.class)
    AuthorFullname authorFullname;

    public Author(Long id, AuthorFullname authorFullname, String biography, LocalDate date) {
        this.id = id;
        this.authorFullname=authorFullname;
        this.biography = biography;
        this.dateOfBirth = date;
    }
    public Author(Long id , String name, String surname)
    {
        this.id=id;
        this.authorFullname = new AuthorFullname(name,surname);

    }
    public Author() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getBiography() {
        return biography;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getFormattedBirth() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return dateOfBirth.format(formatter);
    }

}
