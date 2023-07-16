package org.acme.resource;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.acme.models.Book;
import org.acme.services.BookService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import java.util.List;
import java.util.Optional;


@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {
    @Inject
    BookService bookService;

    @POST
    @RolesAllowed({"admin"})
    @Operation(summary = "Add a new book")
    @APIResponses(value = {
            @APIResponse(responseCode = "201", description = "Book added successfully"),
            @APIResponse(responseCode = "400", description = "Invalid book details")
    })
    public Response addBook(@Valid Book book) {
        Book addedBook = bookService.addBook(book);
        return Response.status(Response.Status.CREATED).entity(addedBook).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed({"admin"})
    @Operation(summary = "Update an existing book")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Book updated successfully"),
            @APIResponse(responseCode = "400", description = "Invalid book details"),
            @APIResponse(responseCode = "404", description = "Book not found")
    })
    public Response updateBook(@PathParam("id") Integer id, @Valid Book book) {
        book.setId(id);
        Book updatedBook = bookService.updateBook(book);
        if (updatedBook != null) {
            return Response.ok(updatedBook).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"admin"})
    @Operation(summary = "Delete a book")
    @APIResponses(value = {
            @APIResponse(responseCode = "204", description = "Book deleted successfully"),
            @APIResponse(responseCode = "404", description = "Book not found")
    })
    public Response deleteBook(@PathParam("id") Long id) {
        bookService.deleteBook(id);
        return Response.noContent().build();
    }

    @GET
    @Operation(summary = "Get all books")
    public List<Book> getAllBooks() {
        return bookService.findAllBooks();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get a book by ID")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Book found"),
            @APIResponse(responseCode = "404", description = "Book not found")
    })
    public Response getBookById(@PathParam("id") Long id) {
        Optional<Book> optionalBook = bookService.findBookById(id);
        if (optionalBook.isPresent()) {
            return Response.ok(optionalBook.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}

