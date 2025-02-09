package commands;

import control.Buyable;
import exceptions.CommandExecuteException;
import exceptions.CommandParseException;
import logic.Game;

public class GrenadeCommand extends Command implements Buyable{

	private static String INCORRECT_NUMBER_OF_ARGS_MSG = "Incorrect number of arguments";
	
	private static String NAME = "grenade";
	private static String DETAILS = "[g]renade <x> <y>";
	private static String SHORTCUT = "g";
	private static String HELP = "add a grenade in position x, y";
	private int x;
	private int y;	

	public GrenadeCommand(int x, int y) {
		super(NAME, SHORTCUT, DETAILS, HELP);
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean execute (Game game) throws CommandExecuteException {
		try {
			buy(game); 
			game.cGrenade(this.x,this.y);
		}
		catch (CommandExecuteException e) {
			System.out.println(e.getMessage());
			throw new CommandExecuteException();
		}
		return true;
	}

	@Override
	public int cost() {
		return 3;
	}
	
	@Override
	protected Command parse(String[] words) throws CommandParseException, NumberFormatException{
		if (matchCommandName(words[0]))
			if (words.length != 3) {
				throw new CommandParseException(String.format("Command %s: %s", NAME, INCORRECT_NUMBER_OF_ARGS_MSG));
			} 	
			else { 
				try {
					return new GrenadeCommand(Integer.parseInt(words[1]), Integer.parseInt(words[2]));
				}
				catch(NumberFormatException ex) {
					throw new NumberFormatException("The second and third arguments have to be integers");
				}
			}
	return null;
	}
}
