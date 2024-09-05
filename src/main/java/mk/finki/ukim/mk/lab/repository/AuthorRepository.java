package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Author;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
public class AuthorRepository {
    List<Author> autorsList;

//    public AuthorRepository() {
//        autorsList = new ArrayList<>();
//        autorsList.add(new Author(111L, "Michael", "Charls", ""));
//        autorsList.add(new Author(222L, "Mikle", "Klodvi", ""));
//        autorsList.add(new Author(333L, "Kristi", "Lopez", ""));
//        autorsList.add(new Author(444L, "Mia", "Serafimova", ""));
//        autorsList.add(new Author(555L, "Karl", "Karlovic", ""));
//
//    }

    public List<Author> findAll(){
    return  autorsList;
    }

    public Optional<Author> findById(Long id){
       // Author author = new Author();
    for(int i = 0; i<autorsList.size();i++) {
        if (autorsList.get(i).getId().equals(id)) {
            return Optional.ofNullable(autorsList.get(i));
        }
    }
    return null;
    }
}
