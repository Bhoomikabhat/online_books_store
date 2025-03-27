package com.assighnment.online_book_store;

import java.util.List;
import java.util.Optional;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.assighnment.online_book_store.dao.BookRepository;
import com.assighnment.online_book_store.entities.Books;




@SpringBootApplication
public class OnlineBookStoreApplication {

	public static void main(String[] args) {
	ApplicationContext context=SpringApplication.run(OnlineBookStoreApplication.class, args);
	
	BookRepository bookrepo=context.getBean(BookRepository.class);
//	Books book1=new Books();
//	book1.setId(111);
//	book1.setTitle(" Rama the King");
//	book1.setAuthor("Janaki");
//	book1.setPrice(1000);
//	
//	Books book2=new Books();
//	book1.setId(222);
//	book1.setTitle(" Five Goes Down the Sea");
//	book1.setAuthor("AHS");
//	book1.setPrice(350);
//	
	//List<Books> book=List.of(book1,book2);
//	bookrepo.saveAll(book);
	//Books b=bookrepo.save(book1);
	//System.out.println(b);
//	Optional<Books> op=bookrepo.findById(111);
//	Books book=op.get();
//	System.out.println(book);
	}

}
