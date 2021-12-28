package com.bootrestjpaapi.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootrestjpaapi.test.entity.Book;
import com.bootrestjpaapi.test.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	
	// fetching entire book data
	public List<Book> getAllBooks()
	{
		List<Book> list = (List<Book>)this.bookRepository.findAll();
		return list;
	}
	
	// fetching single book data
	public Book getBookById(int id)
	{
		Book b=null;
		try
		{
			b = this.bookRepository.findById(id);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return b;
	}
	
	// adding single book data
	public Book addBook(Book b)
	{
		Book addedBook = this.bookRepository.save(b);
		return addedBook;
	}
	
	//Deleting a book from db
	public void deleteBook(int id)
	{
		this.bookRepository.deleteById(id);
	}
	
	// Update a book inside db
	public void updateBook(int id,Book b)
	{
		b.setId(id);
		this.bookRepository.save(b);
	}
}
