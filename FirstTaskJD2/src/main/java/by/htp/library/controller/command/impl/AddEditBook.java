package by.htp.library.controller.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.library.controller.command.Command;
import by.htp.library.service.BookService;
import by.htp.library.service.exception.ServiceException;


public class AddEditBook implements Command {
	
	private static final Logger log=LogManager.getRootLogger();
	private static final String DELIMETER =" ";
	private static final String BOOK_SUCCESSFULLY_EDITED ="Book successfully edited!";
	private static final String ERROR_EDITING_BOOK ="Error editing book!";
	private static final String LOG_FAIL_ADDEDITBOOK ="Fail in addEditBook!";
	
	
	
	private BookService bookService;
	
	public AddEditBook(){
		super();
	}
	
		public AddEditBook(BookService bookService){
			super();
			this.bookService= bookService;
		}
	

	@Override
	public String executeCommand(String request) {
		String [] parameter = request.split( DELIMETER );
		String title = parameter[1];
		String author = parameter[2];
		String genre = parameter[3];
		String year = parameter[4];
		String quantity = parameter[5];
		String idBook = parameter[6];

		
		String response = null;
		
		try {
			bookService.addEditBook(title, genre, author, year, quantity, idBook);
			response = BOOK_SUCCESSFULLY_EDITED;
		} catch (ServiceException e) {
			response = ERROR_EDITING_BOOK;
			log.error(LOG_FAIL_ADDEDITBOOK);
		}
		
		return response;
	}

}
