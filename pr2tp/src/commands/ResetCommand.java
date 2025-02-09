package commands;
import exceptions.CommandExecuteException;
import exceptions.CommandParseException;
import logic.Game;

public class ResetCommand extends Command{
	
	private static String INCORRECT_NUMBER_OF_ARGS_MSG = "Incorrect number of arguments";
	
	private static String NAME = "reset";
	private static String DETAILS = "[r]eset [<level> <seed>]";
	private static String SHORTCUT = "r";
	private static String HELP = "reset the game";
	private String newLevel;
	private Long newSeed;
	
	public ResetCommand(String newLevel, Long newSeed) {
		super(NAME, SHORTCUT, DETAILS, HELP);
		this.newLevel = newLevel;
		this.newSeed = newSeed;
	}
	
	public boolean execute(Game game) throws CommandExecuteException{
		try {
			game.cReset(newLevel, newSeed);
		}
		catch (CommandExecuteException e) {
			System.out.println(e.getMessage());
			throw new CommandExecuteException();
		}
		return true;
	}
	
	protected Command parse(String[] words) throws CommandParseException{
		if (matchCommandName(words[0]))
			if (words.length != 3) {
				throw new CommandParseException(String.format("Command %s: %s", NAME, INCORRECT_NUMBER_OF_ARGS_MSG));
			} 	
			else { 
				try {
					return new ResetCommand(words[1], Long.parseLong(words[2]));
				}
				catch(NumberFormatException ex) {
					throw new NumberFormatException("The third argument has to be long");
				}
				
			}
	return null;
	}
}
