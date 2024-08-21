package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
public class BookStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String city;
    private String address;

    public BookStore( String name, String city, String address) {
        this.id=(long) (Math.random()*1000);
        this.name = name;
        this.city = city;
        this.address = address;
    }

    public BookStore() {
    }

}

