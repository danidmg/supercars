package view;

import logic.Game;

public class GameSerializer {

	private static final String TITLE = "---- ROAD FIGHTER ----";
	
	private Game game;
	
	public GameSerializer(Game game) {
		this.game = game;
	}
	
	public String toString() {
		
		StringBuilder str = new StringBuilder();
		
		str.append(TITLE);
		str.append("\n");
		str.append(game.getSerializeInfo());
		
		
		return str.toString();
	}
	
}
