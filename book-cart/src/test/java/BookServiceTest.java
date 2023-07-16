import io.quarkus.test.junit.QuarkusTest;
import org.acme.models.Book;
import org.acme.models.BookType;
import org.acme.services.BookService;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class BookServiceTest {

    @Inject
    BookService bookService;

    @Test
    public void testAddBook() {
        // Test the addBook method

        // Create a Book for testing
        // Set book properties
        Book book = Book.builder()
                .price(100)
                .title("Rich Dad & Poor Dad")
                .type(BookType.findById(1))
                .author("Robit Kiy")
                .isbn("GHSJYE7899HWNE")
                .description("This book world famous book.")
                .build();


        // Call the method
        Book result = bookService.addBook(book);

        // Validate the result is not null
        assertNotNull(result);
    }

    @Test
    public void testUpdateBook() {
        // Test the updateBook method

        Book book = Book.findById(1);

        book.setPrice(189);

        // Call the method
        Book result = bookService.updateBook(book);

        // Validate the result is not null
        assertNotNull(result);
    }

    @Test
    public void testDeleteBook() {
        // Test the deleteBook method

        // Create a book for testing
        Book book = Book.builder()
                .price(100)
                .title("C++")
                .type(BookType.findById(1))
                .author("Jmes")
                .isbn("GHSJsfdfE7899HWNE")
                .description("This book good for cpp.")
                .build();
        // Add the book to the database
        book.persist();

        // Call the method
        bookService.deleteBook(book.getId().longValue());

        // Try to find the book by ID
        Optional<Book> deletedBook = bookService.findBookById(book.getId().longValue());

        // Validate the book is not found (deleted)
        assertFalse(deletedBook.isPresent());
    }

    @Test
    public void testFindAllBooks() {
        // Test the findAllBooks method

        Book book1 = Book.builder()
                .price(100)
                .title("C++")
                .type(BookType.findById(1))
                .author("Jmes")
                .isbn("GHSJsfdfE7899HWNE")
                .description("This book good for cpp.")
                .build();

        Book book2 = Book.builder()
                .price(100)
                .title("Rich Dad & Poor Dad")
                .type(BookType.findById(1))
                .author("Robit Kiy")
                .isbn("GHSJYE7899HWNE")
                .description("This book world famous book.")
                .build();

        // Add the book to the database
        book1.persist();
        book2.persist();

        // Call the method
        List<Book> books = bookService.findAllBooks();

        // Validate the returned list is not empty
        assertFalse(books.isEmpty());
        // Validate the number of books matches the expected count
        assertEquals(2, books.size());
    }

    @Test
    public void testFindBookById() {
        // Test the findBookById method

        // Create a book for testing
        Book book = Book.builder()
                .price(100)
                .title("Java")
                .type(BookType.findById(2))
                .author("Hawkins")
                .isbn("GHSJs899HWNE")
                .description("This book good for Java.")
                .build();

        // Add the book to the database
        book.persist();

        // Call the method
        Optional<Book> foundBook = bookService.findBookById(book.getId().longValue());

        // Validate the book is found
        assertTrue(foundBook.isPresent());
        // Validate the found book matches the original book
        assertEquals(book.getId(), foundBook.get().getId());
        // Validate other book properties
        // ...
    }
}
