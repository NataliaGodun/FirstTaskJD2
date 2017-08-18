package by.htp.library.controller;

import by.htp.library.controller.command.CommandProvider;
import by.htp.library.controller.command.Command;

public final class Controller {
	
	private static final char PARAM_DELIMETER = ' ';
	
	private CommandProvider provider;
	
	public  Controller(){
		super();
	}
	
	public  Controller(CommandProvider provider){
		super();
		this.provider= provider;
	}
	
	
	public String executeAction(String request){
		String commandName;
		Command command;
		
		commandName = request.substring(0, request.indexOf(PARAM_DELIMETER));
		
		command = provider.getCommand(commandName);
		String response = command.executeCommand(request);
		
		return response;
	}
}
