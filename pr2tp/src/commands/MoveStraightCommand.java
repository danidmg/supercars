package commands;

import logic.Game;

public class MoveStraightCommand extends Command{
	
	private static String NAME = "none";
	private static String DETAILS = "[n]one | []";
	private static String SHORTCUT = "";  // le pongo esto porque siempre le damos solo al enter, nunca se toma la molestia de poner la n
	private static String HELP = "just update the game";
	
	public MoveStraightCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
	
	public boolean execute(Game game) {
		game.cMoveStraight();
		return true;
	}
}