package booksStandalone.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import booksStandalone.dao.BookDAO;
import booksStandalone.model.Author;
import booksStandalone.model.Book;
import booksStandalone.model.Category;
import booksStandalone.util.database.DataBaseConnection;

public class BookDAOImpl implements BookDAO {

	private Connection connection = null;
	private PreparedStatement statement = null;
	private ResultSet resultSet = null;

	public BookDAOImpl() {
		this.connection = DataBaseConnection.getConection();
	}

	public List<Book> findAllBooks() {
		List<Book> result = new ArrayList<>();
		List<Author> authorList = new ArrayList<>();
		String sql = "select * from BOOK inner join AUTHOR on BOOK.id = AUTHOR.book_id";

		try {

			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Book book = new Book();
				Author author = new Author();
				book.setId(resultSet.getLong("id"));
				book.setBookTitle(resultSet.getString("book_title"));
				book.setCategoryId(resultSet.getLong("category_id"));
				author.setBookId(resultSet.getLong("book_Id"));
				author.setFirstName(resultSet.getString("first_name"));
				author.setLastName(resultSet.getString("last_name"));
				authorList.add(author);
				book.setAuthors(authorList);
				book.setPublisherName(resultSet.getString("publisher"));
				result.add(book);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DataBaseConnection.setEndConnection(connection);
		}
		return result;
	}

	public List<Book> searchBooksByKeyword(String keyWord) {
		List<Book> result = new ArrayList<>();
		List<Author> authorList = new ArrayList<>();
		String sql = "select * from BOOK inner join AUTHOR on BOOK.id = AUTHOR.book_id" + " where book_title like '%"
				+ keyWord.trim() + "%'" + " or first_name like '%" + keyWord.trim() + "%'" + " or last_name like '%"
				+ keyWord.trim() + "%'";

		try {
			connection = DataBaseConnection.getConection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Book book = new Book();
				Author author = new Author();
				book.setId(resultSet.getLong("id"));
				book.setBookTitle(resultSet.getString("book_title"));
				book.setPublisherName(resultSet.getString("publisher"));
				author.setFirstName(resultSet.getString("first_name"));
				author.setLastName(resultSet.getString("last_name"));
				author.setBookId(resultSet.getLong("book_id"));
				authorList.add(author);
				book.setAuthors(authorList);
				result.add(book);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DataBaseConnection.setEndConnection(connection);

		}
		return result;
	}

	public List<Category> findAllCategories() {
		List<Category> result = new ArrayList<>();
		String sql = "select * from CATEGORY";		
		try {
			connection = DataBaseConnection.getConection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Category category = new Category();
				category.setId(resultSet.getLong("id"));
				category.setCategoryDescription(resultSet.getString("category_description"));
				result.add(category);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DataBaseConnection.setEndConnection(connection);
		}
		return result;
	}
	
	//TODO implementar 
	public void insert(Book book) {
	}

	public void update(Book book) {
	}

	public void delete(Long bookId) {
	}
}