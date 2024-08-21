package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.Review;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ReviewRepository {
    List<Review> reviews;

    public ReviewRepository(List<Review> reviews) {
        this.reviews = new ArrayList<>();
        this.reviews = reviews;
    }

    public List<Review> filterByDate(LocalDateTime from, LocalDateTime to) {
        return reviews.stream()
                .filter(review -> review.getTimestamp().isAfter(from) && review.getTimestamp().isBefore(to))
                .collect(Collectors.toList());
    }
}
