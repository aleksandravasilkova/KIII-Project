package mk.finki.ukim.mk.lab.model;

import lombok.Data;

import java.io.Serializable;
@Data
public class AuthorFullname implements Serializable {



        private String name;
        private String surname;
        public AuthorFullname() {
        }

        public AuthorFullname(String name, String surname) {
            this.name = name;
            this.surname = surname;
        }



}
