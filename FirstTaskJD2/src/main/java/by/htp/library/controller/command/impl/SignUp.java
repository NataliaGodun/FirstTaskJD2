package by.htp.library.controller.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.library.controller.command.Command;
import by.htp.library.service.UserService;
import by.htp.library.service.exception.ServiceException;


public class SignUp implements Command {
	private static final Logger log=LogManager.getRootLogger();
	private static final String DELIMETER =" ";
	private static final String MESSAGE_SUCCESSFULLY_REGISTRATION = "User was registrated " ;
	private static final String ERROR_SIGN_UP ="Sign up error";
	private static final String LOG_FAIL_SIGN_UP ="Fail in SignUp!";

	private UserService userService;
	
	public SignUp(){
		super();
	}
	
		public SignUp(UserService userService){
			super();
			this.userService= userService;
		}

	@Override
	public String executeCommand(String request) {
		String [] parameter = request.split(DELIMETER);
		String login = parameter[1];
		String password = parameter[2];
		
		String response = null;
		
		try {
			userService.signUp(login, password);
			response = MESSAGE_SUCCESSFULLY_REGISTRATION  + login;
		} catch (ServiceException e) { 
			response = ERROR_SIGN_UP;
			log.error(LOG_FAIL_SIGN_UP);
		}
		
		return response;
	}

}
