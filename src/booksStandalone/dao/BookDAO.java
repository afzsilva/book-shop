package booksStandalone.dao;

import java.util.List;

import booksStandalone.model.Book;
import booksStandalone.model.Category;

public interface BookDAO {
	
	public List<Book> findAllBooks();

	public List<Book> searchBooksByKeyword(String keyWord);

	public List<Category> findAllCategories();

	public void insert(Book book);

	public void update(Book book);

	public void delete(Long bookId);
}