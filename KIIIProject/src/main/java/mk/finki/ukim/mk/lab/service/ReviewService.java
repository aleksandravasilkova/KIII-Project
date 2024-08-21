package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.Review;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public interface ReviewService {
    List<Review> findAll();
    Optional<Review> save(Integer score, String description, Book book, LocalDateTime timestamp);

    List<Review> filterByDate(LocalDateTime from, LocalDateTime to);


}