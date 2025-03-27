package com.assighnment.online_book_store.service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.assighnment.online_book_store.dao.BookRepository;
import com.assighnment.online_book_store.entities.Books;


@Service
public class BookService
{
	@Autowired
	private BookRepository bookrepo;
	
	private static Logger logger=LoggerFactory.getLogger(BookService.class);
	
	public Books getBooksById(int id)
	{
		logger.debug("Fetching book with ID: {}", id);
		Books book=null;
		Optional<Books> optional=bookrepo.findById(id);
		if (optional.isPresent()) {
            logger.info("Book found: {}", optional.get());
		book=optional.get();
		return book;
		}
		else {
            logger.warn("Book with ID {} not found", id);
            return null;
        }
	}
	
	public List<Books> getAllBooks()
	{
		Iterable<Books> iterable= bookrepo.findAll();
		List<Books> books=(List<Books>)iterable;
		 logger.info("Total books retrieved: {}", books.size());
		return books;
	}
	
	// Add a new book
    public Books addBook(Books book) {
    	
        Books savedBook=bookrepo.save(book);
        logger.info("Book added successfully with ID: {}", book.getId());
        		return savedBook;
    }
    
    // Update an existing book
    public Books updateBook(int id, Books bookDetails) {
        Books book = bookrepo.findById(id).orElseThrow();
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setPrice(bookDetails.getPrice());
        logger.info("Book updated successfully: {}", book);
        return bookrepo.save(book);
    }

    
    
 // Delete a book by ID
    public boolean deleteBook(int id) {
        if (bookrepo.existsById(id)) {
            bookrepo.deleteById(id);
            logger.info("Book with ID {} deleted successfully", id);
            return true;
        }
        logger.warn("Book with ID {} not found, deletion failed", id);
        return false;
    }
}
