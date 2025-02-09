package commands;

import control.Buyable;
import exceptions.CommandExecuteException;
import exceptions.NotEnoughCoinsException;
import logic.Game;
import logic.WaveAction;

public class WaveCommand extends Command implements  Buyable{
	
	private static String NAME = "wave";
	private static String DETAILS = "[w]ave";
	private static String SHORTCUT = "w";
	private static String HELP = "do wave";
	
	public WaveCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException{
		try {
			buy(game);
			WaveAction w = new WaveAction();
			game.actionExecute(w);
		}
		catch(NotEnoughCoinsException e) {
			System.out.println(e.getMessage());
			throw new CommandExecuteException("Unable to execute command");
		}
		return true;
	}

	@Override
	public int cost() {
		return 5;
	}
}
