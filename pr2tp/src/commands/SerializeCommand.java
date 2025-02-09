package commands;

import logic.Game;

public class SerializeCommand extends Command{

	private static String NAME = "serialize";
	private static String DETAILS = "seriali[z]e";
	private static String SHORTCUT = "z";
	private static String HELP = "text-serialize the state of the game";
	
	public SerializeCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) {
		game.cSerialize();
		return false;
	}

}
