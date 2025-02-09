package commands;

import exceptions.CommandExecuteException;
import exceptions.PlayerOutException;
import logic.Game;

public class MoveDownCommand extends Command {
	
	private static String NAME = "a";
	private static String DETAILS = "[a]";
	private static String SHORTCUT = "a";
	private static String HELP = "move car down";
	
	public MoveDownCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	public boolean execute(Game game) throws CommandExecuteException{
		try {
			game.cMoveDown();
		}
		catch (PlayerOutException e) {
			System.out.println(e.getMessage());
			throw new CommandExecuteException();
		}
		return true;
	}
}
