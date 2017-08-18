package by.htp.library.controller.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.library.controller.command.Command;

import by.htp.library.service.UserService;
import by.htp.library.service.exception.ServiceException;


public class SignIn implements Command {
	private static final Logger log=LogManager.getRootLogger();
	private static final String DELIMETER =" ";
	private static final String MESSAGE_WELCOME ="Welcome ";
	private static final String ERROR_SIGN_IN ="Sign in error";
	private static final String LOG_FAIL_SIGN_IN ="Fail in SignIn!";
	
	private UserService userService;
	
	public SignIn(){
		super();
	}
	
		public SignIn(UserService userService){
			super();
			this.userService= userService;
		}

	@Override
	public String executeCommand(String request) {
		String [] parameter = request.split( DELIMETER);
		String login = parameter[1];
		String password = parameter[2];
		
		
		String response = null;
		
		try {
			userService.signIn(login, password);
			response = MESSAGE_WELCOME + login;
		} catch (ServiceException e) { 
			response = ERROR_SIGN_IN;
			log.error(LOG_FAIL_SIGN_IN);;
		}
		
		return response;
	}

}
