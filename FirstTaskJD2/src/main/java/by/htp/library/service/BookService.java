package by.htp.library.service;

import java.util.List;

import by.htp.library.bean.Book;
import by.htp.library.service.exception.ServiceException;

public interface BookService {
	void addNewBook(String title, String genre, String author, String year, String quantity) throws ServiceException;
	void addEditBook(String title, String genre, String author, String year, String quantity, String idBook) throws ServiceException;
	List<Book> getBooklist() throws ServiceException;
}
