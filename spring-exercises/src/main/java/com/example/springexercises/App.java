package com.example.springexercises;

import com.example.springexercises.entities.Book;
import com.example.springexercises.repositories.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		System.out.println("||| Init Spring |||");
		ApplicationContext spring = SpringApplication.run(App.class, args);

		BookRepository bookRepository = spring.getBean(BookRepository.class);

		System.out.println("||| Save Book |||");
		Book book1 = new Book(null, "book1", 9.99, 150);
		Book book2 = new Book(null, "book2", 19.99, 350);
		Book book3 = new Book(null, "book3", 29.99, 250);

		bookRepository.save(book1);
		bookRepository.save(book2);
		bookRepository.save(book3);

		System.out.println("||| Find All Books |||");
		List<Book> books = bookRepository.findAll();
		System.out.println(books);

		System.out.println("||| Update Book |||");
		book1.setPrice(15.28);
		bookRepository.save(book1);

		book2.setNumPages(432);
		bookRepository.save(book2);

		System.out.println("||| Delete Book by ID |||");
		bookRepository.deleteById(2L);

		books = bookRepository.findAll();
		System.out.println(books);

		System.out.println("||| Find Book by ID |||");
		Optional<Book> bookOptional = bookRepository.findById(1L);

		if (bookOptional.isPresent()) {
			Book book = bookOptional.get();
			System.out.println(book.getTitle());
		} else {
			System.out.println("There is no book with this ID");
		}

	}

}
