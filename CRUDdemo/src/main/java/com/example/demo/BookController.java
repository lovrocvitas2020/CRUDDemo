package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
	
	@Autowired
	BookRepository bookRepository;
	
	/**
	 * Metho for fetching all books
	 * @return
	 */
	@GetMapping("/getAllBooks")
	public List<Book> getAllBooks(){
		
	return bookRepository.findAll();
	
	}

	/**
	 * Method for fetching books by id
	 * @param id
	 * @return
	 */
	@GetMapping("/getBookById/{id}")
	public Optional<Book> getBookById(@PathVariable(value="id") Long id){
		return bookRepository.findById(id);
	}
	
	/**
	 * Method for adding books
	 * @param book
	 * @return
	 */
	@PostMapping("/addBook")
	public Book addBook(@RequestBody Book book){
		
		return bookRepository.save(book);
	}

	/**
	 * Method for updating books
	 * @param id
	 * @param bookDetails
	 * @return
	 */
	@PutMapping("/updateBook/{id}")
	public Book updateBook(@PathVariable(value="id") Long id, @RequestBody Book bookDetails)
	{
		Optional<Book> book = bookRepository.findById(id);
		Book book_new=book.get();
		book_new.setAuthor_name(bookDetails.getAuthor_name());
		book_new.setBook_name(bookDetails.getBook_name());
		book_new.setIsbn(bookDetails.getIsbn());
	
		return bookRepository.save(book_new);

	}

	/**
	 * Method for deleting books
	 * @param id
	 */
	@DeleteMapping("/deleteBook/{id}")
	public void deleteBook(@PathVariable(value="id") Long id)
	{
		Optional<Book> book=bookRepository.findById(id);
		Book book_new=book.get();
	
	bookRepository.delete(book_new);
	}
	
}
