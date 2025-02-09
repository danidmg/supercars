package commands;

import logic.Game;

public class TestCommand extends Command{
	
	private static String NAME = "test";
	private static String DETAILS = "[t]est";
	private static String SHORTCUT = "t";
	private static String HELP = "activate test mode";
	
	public TestCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
	
	public boolean execute(Game game) {
		game.cTest();
		return false;
	}
}
