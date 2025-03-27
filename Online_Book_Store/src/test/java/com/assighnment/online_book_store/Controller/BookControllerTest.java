package com.assighnment.online_book_store.Controller;



import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.assighnment.online_book_store.entities.Books;
import com.assighnment.online_book_store.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean // Use @MockBean instead of @Mock for Spring-managed beans
    private BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBooks() throws Exception {
        Books book1 = new Books();
        book1.setId(1);
        book1.setTitle("Java 101");

        Books book2 = new Books();
        book2.setId(2);
        book2.setTitle("Spring Boot Guide");

        when(bookService.getAllBooks()).thenReturn(Arrays.asList(book1, book2));

        mockMvc.perform(get("/books"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void testGetBookById() throws Exception {
        Books book = new Books();
        book.setId(1);
        book.setTitle("Mockito Testing");

        when(bookService.getBooksById(1)).thenReturn(book);

        mockMvc.perform(get("/books/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.title").value("Mockito Testing"));
    }

    @Test
    void testAddBook() throws Exception {
        Books book = new Books();
        book.setId(1);
        book.setTitle("New Book");

        when(bookService.addBook(any(Books.class))).thenReturn(book);

        mockMvc.perform(post("/books")
               .contentType(MediaType.APPLICATION_JSON)
               .content(new ObjectMapper().writeValueAsString(book)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.title").value("New Book"));
    }
}
