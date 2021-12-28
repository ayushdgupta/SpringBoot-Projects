package com.bootrestjpaapi.test.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bootrestjpaapi.test.entity.Book;
import com.bootrestjpaapi.test.service.BookService;


// complete example of spring boot rest + JPA but here custom query code is not
// present
@RestController
public class BookController {

	@Autowired
	private BookService bookService;
	
	// Get all book handler
	@GetMapping("/books")
	public ResponseEntity<List<Book>> getBooks()
	{
		List<Book> list = this.bookService.getAllBooks();
		if(list.isEmpty())
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(list);
		// returning data along with custom status
	}
	
	// Get book by id handler
	@GetMapping("/books/{id}")
	public ResponseEntity<Book> getBookViaId(@PathVariable("id") int id)
	{
		Book b = this.bookService.getBookById(id);
		if(b==null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
			return ResponseEntity.of(Optional.of(b));
	}
		
	// Add book handler
	@PostMapping("/books")
		public ResponseEntity<Book> addNewBookData(@RequestBody Book book)
		{
			Book b = null;
			try
			{
				b = this.bookService.addBook(book);
				System.out.println("Data added to db");
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			return ResponseEntity.of(Optional.of(b));
		}	
	
	// delete book handler
	@DeleteMapping("/books/{id}")
	public ResponseEntity<Book> deleteBookById(@PathVariable("id") int id)
	{
		try
		{
			this.bookService.deleteBook(id);
			System.out.println("Data deleted from db");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	// update book handler
	@PutMapping("/books/{id}")
	public ResponseEntity<Book> updateteBookById(@RequestBody Book book , @PathVariable("id") int id)
	{
		try
		{
			this.bookService.updateBook(id, book);
			System.out.println("Data updated in db");
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	
}
