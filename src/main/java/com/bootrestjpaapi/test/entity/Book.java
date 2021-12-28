package com.bootrestjpaapi.test.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="books")			// this is used to rename the table from 
public class Book {				// Book to books
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="bookId")			// this is used to rename column and can 
	private int id;					// be applied to anyone
	private String title;
	// here Author is refered from different entity so we need to specify mapping
	@OneToOne(cascade = CascadeType.ALL)
	@JsonManagedReference
	private Author author;
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Book(int id, String title, Author author) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + "]";
	}
	
}
