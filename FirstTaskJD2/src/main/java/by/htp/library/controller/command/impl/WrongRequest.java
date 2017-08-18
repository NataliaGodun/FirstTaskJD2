package by.htp.library.controller.command.impl;

import by.htp.library.controller.command.Command;

public class WrongRequest implements Command {
	private static final String MESSAGE_WRONG_REQUEST = "Wrong request!";
	
	public WrongRequest(){
		super();
	}

	@Override
	public String executeCommand(String request) {
		return MESSAGE_WRONG_REQUEST;
	}

}
