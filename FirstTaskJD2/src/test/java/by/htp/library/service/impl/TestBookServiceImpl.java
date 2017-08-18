package by.htp.library.service.impl;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import by.htp.library.dao.connection.ConnectionPool;
import by.htp.library.dao.exception.ConnectionPoolException;
import by.htp.library.service.BookService;
import by.htp.library.service.exception.ServiceException;

public class TestBookServiceImpl {
	private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
			"applicationContext.xml");
	private BookService bookService = context.getBean("BookServiceImpl", BookService.class);
	private static ConnectionPool connectionPool = context.getBean("ConnectionPool", ConnectionPool.class);

	@BeforeClass
	public static void initSource() throws ConnectionPoolException {

		connectionPool.init();
	}

	@AfterClass
	public static void destroySource() throws ConnectionPoolException, IOException {

		connectionPool.close();
	}

	@Test(expected = ServiceException.class)
	public void testAddNewBook() throws ServiceException {
		bookService.addNewBook(null, null, null, null, null);
	}

	@Test
	public void testAddEditBook() {
		try {
			bookService.addEditBook(null, "MyAuthor", "MyGenre", "2017", "10", "1");
		} catch (ServiceException e) {
			Assert.assertEquals("Incorrect data about book", e.getMessage());
		}
	}

}
