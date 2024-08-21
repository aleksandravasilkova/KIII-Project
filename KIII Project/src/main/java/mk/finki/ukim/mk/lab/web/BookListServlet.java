package mk.finki.ukim.mk.lab.web;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.model.Book;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;
import mk.finki.ukim.mk.lab.service.BookService;

import java.io.IOException;
import java.util.List;


@WebServlet(name = "BookList", urlPatterns = "/listBooks/servlet")
public class BookListServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final BookService bookService;

    BookListServlet (SpringTemplateEngine springTemplateEngine, BookService bookService){
        this.springTemplateEngine = springTemplateEngine;
        this.bookService=bookService;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        IWebExchange exchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext context= new WebContext(exchange);
        List<Book> bookList = bookService.listBooks();
        context.setVariable("books", bookList);
        this.springTemplateEngine.process("listBooks.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String isbn = req.getParameter("bookIsbn");
        if (isbn == null) {
            return;
        }
        Book book = bookService.findBookByIsbn(isbn);
        IWebExchange exchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(exchange);
        context.setVariable("bookIsbn", book.getIsbn());
        springTemplateEngine.process("listBooks.html", context, resp.getWriter());


    }
}
