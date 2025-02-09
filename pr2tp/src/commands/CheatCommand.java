package commands;

import exceptions.CommandExecuteException;
import exceptions.CommandParseException;
import logic.Game;

public class CheatCommand extends Command{

	private static String INCORRECT_NUMBER_OF_ARGS_MSG = "Incorrect number of arguments";
	
	private static String NAME = "cheat";
	private static String DETAILS = "Cheat <AO-name>";
	private static String SHORTCUT = "cheat";
	private static String HELP = "Removes all elements of last visible column and adds advanced object AO";
	
	private String advancedGe;
	
	public CheatCommand(String advancedGe) {
		super(NAME, SHORTCUT, DETAILS, HELP);
		this.advancedGe =advancedGe;
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException{
		try {
			game.cCheat(this.advancedGe);
		}
		catch (CommandExecuteException e) {
			System.out.println(e.getMessage());
			throw new CommandExecuteException();
		}
		return true;
	}	
	
	@Override
	protected Command parse(String[] words) throws CommandParseException{
		if (matchCommandName(words[0]))
			if (words.length != 2) {
				throw new CommandParseException(String.format("Command %s: %s", NAME, INCORRECT_NUMBER_OF_ARGS_MSG));
			} 	
			else { 
				return new CheatCommand(words[1]);
			}
	return null;
	}
}