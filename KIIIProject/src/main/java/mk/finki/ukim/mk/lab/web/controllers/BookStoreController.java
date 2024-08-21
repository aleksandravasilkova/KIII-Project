package mk.finki.ukim.mk.lab.web.controllers;

import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;
import mk.finki.ukim.mk.lab.repository.jpa.BookStoreRepositoryJpa;
import mk.finki.ukim.mk.lab.service.BookStoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/bookstores")
public class BookStoreController {
    private final BookStoreService bookStoreService;

    public BookStoreController(BookStoreService bookStoreService) {
        this.bookStoreService = bookStoreService;
    }

    @GetMapping()
    public String bookStoreList(Model model){
        List<BookStore> bookStores = bookStoreService.findAll();
        model.addAttribute("bookstores", bookStores);
        return "bookstores";
    }

    @GetMapping("/add")
    public String addBookStore(Model model){
        return "add-bookstore";
    }
    @PostMapping("/add")
    public String saveBook(@RequestParam String name,
                           @RequestParam String city,
                           @RequestParam String address){
        bookStoreService.save(name, city, address);
        return "redirect:/bookstores";
    }
}
