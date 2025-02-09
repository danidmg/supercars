package commands;

import logic.Game;

public class ExitCommand extends Command {
	
	private static String NAME = "exit";
	private static String DETAILS = "[e]xit";
	private static String SHORTCUT = "e";
	private static String HELP = "exit the game";
	
	public ExitCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	public boolean execute(Game game) {
		game.cExit();
		return true;
	}
}