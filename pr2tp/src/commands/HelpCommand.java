package commands;

import logic.Game;

public class HelpCommand extends Command{
	
	private static String NAME = "help";
	private static String DETAILS = "[h]elp";
	private static String SHORTCUT = "h";
	private static String HELP = "show this help";
	
	public HelpCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
	
	public boolean execute(Game game) {
		System.out.print(Command.cHelp());
		return false;
	}
}
