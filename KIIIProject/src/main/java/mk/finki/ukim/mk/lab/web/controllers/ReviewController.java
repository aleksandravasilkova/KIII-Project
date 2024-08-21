package mk.finki.ukim.mk.lab.web.controllers;

import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.Review;
import mk.finki.ukim.mk.lab.service.BookService;
import mk.finki.ukim.mk.lab.service.ReviewService;
import mk.finki.ukim.mk.lab.service.ReviewService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    private final BookService bookService;

    public ReviewController(ReviewService reviewService, BookService bookService) {
        this.reviewService = reviewService;
        this.bookService = bookService;
    }


    @GetMapping("/filtered")
    public String getFilteredReviews(Model model,
                                     @RequestParam(required = false)  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime from,
                                     @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime to) {
        System.out.println("Received fromDate: " + from);
        System.out.println("Received toDate: " + to);

        List<Review> filteredReviews = reviewService.filterByDate(from, to);
        model.addAttribute("reviews", filteredReviews);
        return "reviews";
    }



    @GetMapping("/add")
    public String addReview(Model model){
        return "add-review";
    }
    @PostMapping("/add")
    public String saveReview(@RequestParam int score,
                             @RequestParam String description,
                             @RequestParam String bookIsbn,
                             @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime timestamp){
        Book book = bookService.findBookByIsbn(bookIsbn);
        reviewService.save(score, description,  book, timestamp);
        return "redirect:/reviews";
    }

    @GetMapping
    public String getReviews(@RequestParam(name = "fromDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
                             @RequestParam(name = "toDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
                             Model model) {
        List<Review> reviews;

            reviews = reviewService.findAll();

        model.addAttribute("reviews", reviews);
        return "reviews";
    }

}
