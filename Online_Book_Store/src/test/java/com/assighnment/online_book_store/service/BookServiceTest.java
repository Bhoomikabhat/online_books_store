package com.assighnment.online_book_store.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.assighnment.online_book_store.dao.BookRepository;
import com.assighnment.online_book_store.entities.Books;

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBooksById() {
        Books book = new Books();
        book.setId(1);
        book.setTitle("The Heaven");
        book.setAuthor("ABC");
        book.setPrice(500);

        when(bookRepository.findById(1)).thenReturn(Optional.of(book));

        Books foundBook = bookService.getBooksById(1);

        assertNotNull(foundBook);
        assertEquals("The Heaven", foundBook.getTitle());
        assertEquals("ABC", foundBook.getAuthor());
        assertEquals(500, foundBook.getPrice());
    }

    @Test
    void testGetAllBooks() {
        Books book1 = new Books();
        book1.setId(1);
        book1.setTitle("Java Basics");

        Books book2 = new Books();
        book2.setId(2);
        book2.setTitle("Spring Boot");

        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));

        assertEquals(2, bookService.getAllBooks().size());
    }

    @Test
    void testAddBook() {
        Books book = new Books();
        book.setId(1);
        book.setTitle("New Book");

        when(bookRepository.save(book)).thenReturn(book);

        Books savedBook = bookService.addBook(book);

        assertNotNull(savedBook);
        assertEquals("New Book", savedBook.getTitle());
    }

    @Test
    void testUpdateBook() {
        Books existingBook = new Books();
        existingBook.setId(1);
        existingBook.setTitle("Old Title");
        existingBook.setAuthor("Author A");
        existingBook.setPrice(500);

        Books newDetails = new Books();
        newDetails.setTitle("New Title");
        newDetails.setAuthor("Author B");
        newDetails.setPrice(600);

        when(bookRepository.findById(1)).thenReturn(Optional.of(existingBook));
        when(bookRepository.save(any(Books.class))).thenReturn(existingBook);

        Books updatedBook = bookService.updateBook(1, newDetails);

        assertEquals("New Title", updatedBook.getTitle());
        assertEquals("Author B", updatedBook.getAuthor());
        assertEquals(600, updatedBook.getPrice());
    }

    @Test
    void testDeleteBook() {
        when(bookRepository.existsById(1)).thenReturn(true);

        boolean result = bookService.deleteBook(1);

        assertTrue(result);
        verify(bookRepository, times(1)).deleteById(1);
    }
}
