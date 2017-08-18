package by.htp.library.controller.command;


import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class CommandProvider {
	private static final Logger log=LogManager.getRootLogger();
	
	private  Map<CommandName, Command> map;
	
	public CommandProvider() {
		super();
	}
	

	public CommandProvider(Map<CommandName, Command> map) {
		super();
		this.map=map;
	}
	
	public Command getCommand(String key){
		Command command;
		CommandName commandName;
		
		try{
			commandName = CommandName.valueOf(key.toUpperCase());
			command = map.get(commandName);			
		}catch (IllegalArgumentException | NullPointerException e) {
			log.error("Fail in CommandProvider");
			command = map.get(CommandName.WRONG_REQUEST);
		}
		
		return command;
	}
	
}
