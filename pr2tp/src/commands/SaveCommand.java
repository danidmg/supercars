package commands;

import java.io.IOException;

import exceptions.CommandExecuteException;
import exceptions.CommandParseException;
import logic.Game;

public class SaveCommand extends Command{

	private static String NAME = "save";
	private static String DETAILS = "sa[v]e";
	private static String SHORTCUT = "v";
	private static String HELP = "save the state of the game to a file";
	private static String INCORRECT_NUMBER_OF_ARGS_MSG = "Incorrect number of arguments";
	
	private String file;
	
	public SaveCommand(String file) {
		super(NAME, SHORTCUT, DETAILS, HELP);
		this.file = file;
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException{
		try {
		game.cSave(file);
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
				return new SaveCommand(words[1]);
			}
	return null;
	}
	
}