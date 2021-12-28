package com.bootrestjpaapi.test.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bootrestjpaapi.test.entity.Book;

// Repository means DAO Layer

@Repository
public interface BookRepository extends CrudRepository<Book, Integer>  {
	
	public Book findById(int id);
}
