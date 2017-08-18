package by.htp.library.controller.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.library.controller.command.Command;
import by.htp.library.service.BookService;
import by.htp.library.service.exception.ServiceException;


public class AddNewBook implements Command {
	private static final Logger log=LogManager.getRootLogger();
	private static final String DELIMETER =" ";
	private static final String BOOK_SUCCESSFULLY_ADDED ="Book successfully added!";
	private static final String ERROR_ADDING_BOOK ="Error adding book!";
	private static final String LOG_FAIL_ADDNEWBOOK ="Fail in addNewBook!";
	
	private BookService bookService;
	
	public AddNewBook(){
		super();
	}
	
		public AddNewBook(BookService bookService){
			super();
			this.bookService= bookService;
		}

	@Override
	public String executeCommand(String request) {
		String [] parameter = request.split(DELIMETER);
		String title = parameter[1];
		String author = parameter[2];
		String genre = parameter[3];
		String year = parameter[4];
		String quantity = parameter[5];
		
	
		String response = null;
		
		try {
			bookService.addNewBook(title, genre, author, year, quantity);
			response = BOOK_SUCCESSFULLY_ADDED;
		} catch (ServiceException e) {
			response = ERROR_ADDING_BOOK;
			log.error(LOG_FAIL_ADDNEWBOOK);
		}
		
		return response;
	}

}
