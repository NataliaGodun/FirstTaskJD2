package by.htp.library.service.impl;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import by.htp.library.service.UserService;
import by.htp.library.service.exception.ServiceException;



public class TestUserServiceImpl {
	
	private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
			"applicationContext.xml");
	private UserService userService = context.getBean("UserServiceImpl", UserService.class);
	

/*	���������������� ������������ �� ����������, �.�. �� �� ���������������� ConnectionPool
 	�������������� ����� ����� Connection �������� NullPointerException.
*/
	@Test (expected = NullPointerException.class)
	public void signUp() throws ServiceException{
		
		
		userService.signUp("Dylan O'Brien", "12345678");
	}
}