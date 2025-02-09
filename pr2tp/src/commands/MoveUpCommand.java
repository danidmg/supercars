package commands;

import exceptions.CommandExecuteException;
import exceptions.PlayerOutException;
import logic.Game;

public class MoveUpCommand extends Command {
	
	private static String NAME = "q";
	private static String DETAILS = "[q]";
	private static String SHORTCUT = "q";
	private static String HELP = "move car up";
	
	public MoveUpCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	public boolean execute(Game game) throws CommandExecuteException{
		try {
			game.cMoveUp();
		}
		catch (PlayerOutException e) {
			System.out.println(e.getMessage());
			throw new CommandExecuteException();
		}
		return true;
	}
}