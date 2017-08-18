package by.htp.library.service;

import by.htp.library.service.exception.ServiceException;

public interface UserService {
	void signIn(String login, String password) throws ServiceException;
	void signUp(String login, String password) throws ServiceException;
}
