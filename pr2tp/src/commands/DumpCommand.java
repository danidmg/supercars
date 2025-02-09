package commands;

import java.io.IOException;

import exceptions.CommandExecuteException;
import exceptions.CommandParseException;
import logic.Game;

public class DumpCommand extends Command{

	private static String NAME = "dump";
	private static String DETAILS = "[d]ump";
	private static String SHORTCUT = "d";
	private static String HELP = "show the content of a saved file";
	private static String INCORRECT_NUMBER_OF_ARGS_MSG = "Incorrect number of arguments";
	
	private String file;
	
	public DumpCommand(String file) {
		super(NAME, SHORTCUT, DETAILS, HELP);
		this.file = file;
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException{
		try {
		game.cDump(file);
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
			throw new CommandExecuteException();
		}
		return false;
	}

	protected Command parse(String[] words) throws CommandParseException{
		if (matchCommandName(words[0]))
			if (words.length != 2) {
				throw new CommandParseException(String.format("Command %s: %s", NAME, INCORRECT_NUMBER_OF_ARGS_MSG));
			} 	
			else { 
				return new DumpCommand(words[1]);
			}
	return null;
	}
}