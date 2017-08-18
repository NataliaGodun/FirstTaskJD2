package by.htp.library.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.library.bean.User;

import by.htp.library.dao.UserDAO;
import by.htp.library.dao.exception.DAOException;

import by.htp.library.service.UserService;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.service.validation.ValidationData;

public class UserServiceImpl implements UserService {
	private static final Logger log=LogManager.getRootLogger();
	private static final String MESSAGE_INCORRECT_LOGIN_PASSWORD ="Iccorrent user's login or password";
	private static final String MESSAGE_USER_NOT_FOUND ="User is not found";
	private static final String ERROR_SIGN_IN="Error sign in";
	private static final String ERROR_SIGN_UP ="Error sign up";
	private static final String LOG_FAIL_USERSERVICE ="Fail in  UserServiceImpl!";
	
	private UserDAO userDAO;

	public UserServiceImpl() {
		super();
	}

	public UserServiceImpl(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	@Override
	public void signIn(String login, String password) throws ServiceException {
		if(!ValidationData.validUser(login, password)){
			throw new ServiceException(MESSAGE_INCORRECT_LOGIN_PASSWORD);
		}
		
		
		
		//Attention String_paswword convert to int_password(HashCode)
		try {
			User user = userDAO.signIn(login, password.hashCode());
			if(user == null){
				throw new ServiceException(MESSAGE_USER_NOT_FOUND);
			}
		} catch (DAOException e) {
			log.error(LOG_FAIL_USERSERVICE);
			throw new ServiceException(ERROR_SIGN_IN, e);
		}
	}

	@Override
	public void signUp(String login, String password) throws ServiceException {
		if(!ValidationData.validUser(login, password)){
			throw new ServiceException(MESSAGE_INCORRECT_LOGIN_PASSWORD);
		}
		
		
		//Attention String_paswword convert to int_password(HashCode)
		try {
			userDAO.signUp(login, password.hashCode());
		} catch (DAOException e) {
			log.error(LOG_FAIL_USERSERVICE);
			throw new ServiceException(ERROR_SIGN_UP, e);
		}
	}

}
