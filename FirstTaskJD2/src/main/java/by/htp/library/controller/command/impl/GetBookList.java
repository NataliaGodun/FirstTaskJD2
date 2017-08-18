package by.htp.library.controller.command.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.library.bean.Book;
import by.htp.library.controller.command.Command;
import by.htp.library.service.BookService;
import by.htp.library.service.exception.ServiceException;


public class GetBookList implements Command {
	private static final Logger log=LogManager.getRootLogger();
	
	private static final String BOOKS_SUCCESSFULLY_RECEIVED ="List of books received";
	private static final String ERROR_GETTING_BOOKS ="Error getting list of books";
	private static final String LOG_FAIL_GETBOOKLIST ="Fail in GetBookList!";
	
	private BookService bookService;
	
	public GetBookList(){
		super();
	}
	
		public GetBookList(BookService bookService){
			super();
			this.bookService= bookService;
		}
	@Override
	public String executeCommand(String request) {
		
		
		List<Book> booklist = null;
		String response = null;
		try {
			booklist = bookService.getBooklist();
			response = BOOKS_SUCCESSFULLY_RECEIVED;
			
			//Circle just for test
			for(Book book: booklist){
				System.out.println(book.toString());
			}
		} catch (ServiceException e) {
			response = ERROR_GETTING_BOOKS;
			log.error(LOG_FAIL_GETBOOKLIST);;
		}
		
		return response;
	}

}
