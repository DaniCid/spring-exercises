package com.example.springexercises;

import com.example.springexercises.entities.Book;
import com.example.springexercises.entities.Student;
import com.example.springexercises.entities.Subject;
import com.example.springexercises.repositories.BookRepository;
import com.example.springexercises.repositories.StudentRepository;
import com.example.springexercises.repositories.SubjectRepository;
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
		StudentRepository studentRepository = spring.getBean(StudentRepository.class);
		SubjectRepository subjectRepository = spring.getBean(SubjectRepository.class);

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

		System.out.println("||| Many to Many |||");

		Subject subject1 = new Subject(null, "Math");
		Subject subject2 = new Subject(null, "Tech");

		subjectRepository.saveAll(List.of(subject1, subject2));

		Student student1 = new Student(null, null, "Student1", 25);
		student1.getSubjects().add(subject1);
		student1.getSubjects().add(subject2);

		Student student2 = new Student(null, null, "Student2", 31);
		student2.getSubjects().add(subject1);

	}

}
