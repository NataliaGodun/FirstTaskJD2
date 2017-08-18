package by.htp.library.dao;

import java.util.List;

import by.htp.library.bean.Book;
import by.htp.library.dao.exception.DAOException;

public interface BookDAO {
	void addNewBook(String title, String authro, String genre, String year, int quantity) throws DAOException;
	void addEditBook(String title, String genre, String author, String year, int quantity, int idBook) throws DAOException;
	List<Book> getBooklist() throws DAOException;
}
