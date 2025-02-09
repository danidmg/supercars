package commands;

import control.Buyable;
import exceptions.CommandExecuteException;
import exceptions.NotEnoughCoinsException;
import logic.Game;
import logic.ShootAction;

public class ShootCommand extends Command implements Buyable {

	private static String NAME = "shoot";
	private static String DETAILS = "[s]hoot";
	private static String SHORTCUT = "s";
	private static String HELP = "shoot bullet";
	
	public ShootCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
	
	@Override
	public boolean execute(Game game) throws CommandExecuteException{
		try {
			buy(game);
			ShootAction s = new ShootAction();
			game.actionExecute(s);
		}
		catch(NotEnoughCoinsException e) {
			System.out.println(e.getMessage());
			throw new CommandExecuteException();
		}
		return true;
	}

	@Override
	public int cost() {
		return 1;
	}

	
}