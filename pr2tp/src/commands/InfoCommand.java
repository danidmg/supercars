package commands;

import logic.Game;

public class InfoCommand extends Command{
	

	private static String NAME = "info";
	private static String DETAILS = "[i]nfo";
	private static String SHORTCUT = "i";
	private static String HELP = "print the game element info";
	
	public InfoCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
	
	public boolean execute(Game game) {
		game.cInfo();
		return false;
	}
}
