package by.htp.library.service.impl;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import by.htp.library.service.UserService;
import by.htp.library.service.exception.ServiceException;



public class TestUserServiceImpl {
	
	private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
			"applicationContext.xml");
	private UserService userService = context.getBean("UserServiceImpl", UserService.class);
	

/*	Зарегистрировать пользователя не получиться, т.к. мы не инициализировали ConnectionPool
 	Соответственно когда берем Connection получаем NullPointerException.
*/
	@Test (expected = NullPointerException.class)
	public void signUp() throws ServiceException{
		
		
		userService.signUp("Dylan O'Brien", "12345678");
	}
}