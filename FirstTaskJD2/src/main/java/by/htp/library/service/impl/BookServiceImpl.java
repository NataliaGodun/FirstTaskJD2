package by.htp.library.service.impl;

import java.util.IllegalFormatException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.library.bean.Book;
import by.htp.library.dao.BookDAO;
import by.htp.library.dao.exception.DAOException;

import by.htp.library.service.BookService;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.service.validation.ValidationData;

public class BookServiceImpl implements BookService {
	private static final Logger log=LogManager.getRootLogger();
	private static final String MESSAGE_INCORRECT_DATA_BOOK ="Incorrect data about book";
	private static final String ERROR_YEAR_FORMAT ="Year format exception";
	private static final String ERROR_EDITING_BOOK="Error editing book";
	private static final String MESSAGE_BOOKLIST_NOT_FOUND ="Booklist not found";
	private static final String LOG_FAIL_BOOKSERVICE ="Fail in  BookServiceImpl!";
	private BookDAO bookDAO;

	public BookServiceImpl() {
		super();
	}

	public BookServiceImpl(BookDAO bookDAO) {
		this.bookDAO = bookDAO;
	}
	
	@Override
	public void addNewBook(String title, String genre, String author, String year, String quantityStr) throws ServiceException {
		if(!ValidationData.validBook(title, genre, author, year, quantityStr)){
			throw new ServiceException(MESSAGE_INCORRECT_DATA_BOOK);
		}
	
		int quantity = 0;
		try{
			quantity = Integer.parseInt(quantityStr);
		}catch (IllegalFormatException e) {
			log.error(LOG_FAIL_BOOKSERVICE);
			throw new ServiceException(ERROR_YEAR_FORMAT);
		}
		
	
		
		try {
			bookDAO.addNewBook(title, author, genre, year, quantity);
		} catch (DAOException e) {
			log.error(LOG_FAIL_BOOKSERVICE);
			throw new ServiceException(ERROR_EDITING_BOOK);
		}
		
	}

	@Override
	public void addEditBook(String title, String genre, String author, String year, String quantityStr, String idBookStr) throws ServiceException {
		if(!ValidationData.validBook(title, genre, author, year, quantityStr, idBookStr)){
			throw new ServiceException(MESSAGE_INCORRECT_DATA_BOOK);
		}
		
		int idBook = Integer.parseInt(idBookStr);
		int quantity = Integer.parseInt(quantityStr);
		
		
		try {
			bookDAO.addEditBook(title, genre, author, year, quantity, idBook);
		} catch (DAOException e) {
			log.error(LOG_FAIL_BOOKSERVICE);
			throw new ServiceException(ERROR_EDITING_BOOK);
		}
	}

	@Override
	public List<Book> getBooklist() throws ServiceException {
		
		List<Book> booklist = null;
		
		try {
			booklist = bookDAO.getBooklist();
		} catch (DAOException e) {
			log.error(LOG_FAIL_BOOKSERVICE);
			throw new ServiceException(e);
		}
		
		if(booklist == null){
			throw new ServiceException( MESSAGE_BOOKLIST_NOT_FOUND);
		}
		
		return booklist;
	}

}
