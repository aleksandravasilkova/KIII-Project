package mk.finki.ukim.mk.lab.web.controllers;




import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;
import mk.finki.ukim.mk.lab.service.BookService;
import mk.finki.ukim.mk.lab.service.BookStoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final BookStoreService bookStoreService;

    public BookController(BookService bookService, BookStoreService bookStoreService) {
        this.bookService = bookService;
        this.bookStoreService = bookStoreService;
    }

    @GetMapping
    public String getBooksPage(@RequestParam(required = false) String error, Model model) {
        List<Book> books = this.bookService.listBooks();

        model.addAttribute("books",books);
        return "listBooks";

    }

    @PostMapping ("/add")

    public String saveBook(@RequestParam String title,
                           @RequestParam String isbn,
                           @RequestParam String genre,
                           @RequestParam String year,
                           @RequestParam long id){

        BookStore bookSt = bookStoreService.findAll().stream().filter(b->b.getId().equals(id)).findFirst().get();
        Book book = new Book( isbn,  title,  genre,  Integer.parseInt(year), bookSt );

        bookService.save(book);
        return "redirect:/books";

    }

    @GetMapping("/add")
    public String addBook(Model model){
        List<BookStore> bookStores = bookStoreService.findAll();
        model.addAttribute("bookStores", bookStores);
        return "add-book";
    }

    @GetMapping("/delete/{isbn}")
    public String deleteBook(@PathVariable String isbn){
        Book book = bookService.findBookByIsbn(isbn);
        bookService.listBooks().remove(book);
        return "redirect:/books";

    }

    @GetMapping("/edit/{isbn}")
    public String editBook(@PathVariable String isbn, Model model) {

            model.addAttribute("bookStores", bookStoreService.findAll());
            Book book = this.bookService.findBookByIsbn(isbn);
            model.addAttribute("book",book);
            model.addAttribute("isbn", book.getIsbn());
            model.addAttribute("genre", book.getGenre());
            model.addAttribute("year", book.getYear());
            model.addAttribute("title", book.getTitle());

            model.addAttribute("bookStoreId",Long.parseLong(book.getBookStoreId().toString()));

            return "edit-form";
    }

    @PostMapping("/edit/{isbn}")
    public String editBook(@PathVariable String isbn,
                           @RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam String year,
                           @RequestParam Long bookStoreId){

        Book book = bookService.findBookByIsbn(isbn);
        BookStore bookSt= bookStoreService.findAll().stream().filter(r->r.getId().equals(bookStoreId)).findFirst().get();
        book.setBookStore(bookSt);
        book.setTitle(title);
        book.setGenre(genre);
        book.setYear(Integer.parseInt(year));


        return "redirect:/books";


    }




}
