package org.acme.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.acme.models.Book;


import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class BookService {

    public Book addBook(Book book) {
        book.persist();
        return book;
    }

    public Book updateBook(Book book) {
        return book.getEntityManager().merge(book);
    }

    public void deleteBook(Long id) {
        Book.deleteById(id);
    }

    public List<Book> findAllBooks() {
        return Book.listAll();
    }

    public Optional<Book> findBookById(Long id) {
        return Book.findByIdOptional(id);
    }
}
