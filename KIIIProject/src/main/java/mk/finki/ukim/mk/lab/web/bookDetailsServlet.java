package mk.finki.ukim.mk.lab.web;
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

@WebServlet(urlPatterns = "/servlet/book-details")
public class bookDetailsServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final BookService bookService;

    public bookDetailsServlet(SpringTemplateEngine springTemplateEngine, BookService bookService) {
        this.springTemplateEngine = springTemplateEngine;
        this.bookService = bookService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        IWebExchange exchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext context= new WebContext(exchange);
        String isbn = req.getParameter("bookIsbn");
        Book book = bookService.findBookByIsbn(isbn);
        context.setVariable("book", book);
        springTemplateEngine
                .process("bookDetails.html",context,
                        resp.getWriter());
    }
@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String author = req.getParameter("authorId");
        String isbn = req.getParameter("bookIsbn");
        Long authorId = Long.parseLong(author);
        bookService.addAuthorToBook(authorId, isbn);
        resp.sendRedirect("/book-details?bookIsbn=" + isbn);
    }

}
