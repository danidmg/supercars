package commands;

import logic.Game;

public class ClearCommand extends Command{

	private static String NAME = "clear";
	private static String DETAILS = "Clear [0]";
	private static String SHORTCUT = "0";
	private static String HELP = "Clears the road";
	
	public ClearCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) {
		game.cClear();
		return true;
	}
}
