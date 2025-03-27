package com.assighnment.online_book_store.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.assighnment.online_book_store.entities.Books;
import com.assighnment.online_book_store.service.BookService;


@RestController

public class BookController 
{
	@Autowired
	public BookService bookservice;

	private static Logger logger=LoggerFactory.getLogger(BookService.class);
	
	@RequestMapping(path="/books/{id}",method =RequestMethod.GET)
	public Books getBooks(@PathVariable("id") int id)
	{
		logger.info("Received GET request for book with ID: {}", id);
		return bookservice.getBooksById(id);
	}
	
	@RequestMapping(path="/books",method =RequestMethod.GET)
	public List<Books> getBooks()
	{
		 logger.info("Received GET request for all books");
		return bookservice.getAllBooks();
	}
	
	// Add a new book
	@PostMapping("/books")
	public Books addBook(@RequestBody Books book) {
		logger.info("Received POST request to add book: {}", book);
	    return bookservice.addBook(book);
	}

    
 // Update an existing book
	@PutMapping("/books/{id}")
	public Books updateBook(@PathVariable int id, @RequestBody Books book) {
		logger.info("Received PUT request to update book with ID: {}", id);
	    return bookservice.updateBook(id, book);
	}

    
 // Delete a book by ID
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id) {
    	logger.info("Received DELETE request for book with ID: {}", id);
        return bookservice.deleteBook(id) ? "Book deleted successfully" : "Book not found";
    }
}
