package view;

import logic.Game;
import utils.*;

public class GamePrinter {

	/*
Coin: ¢
Player:
  - Alive: >
  - Crashed: @
Obstacle: ░
Lane separator: ─
Road delimiter: ═
Finish Line: ¦
Wall: ░, ▒, █
SuperCoin: $
Truck: ←
Turbo: >>>
Pedestrian: ☺
Grenade: ð*/
	
	private static final String SPACE = " ";

	//not used in this version
	//private static final String VERTICAL_DELIMITER = "|";

	private static final String ROAD_BORDER_PATTERN = "═";

	private static final String LANE_DELIMITER_PATTERN = "─";

	private static final int CELL_SIZE = 7;

	private static final int MARGIN_SIZE = 2;

	private String indentedRoadBorder;

	private String indentedLlanesSeparator;

	private String margin;


	private static final String CRASH_MSG = String.format("Player crashed!%n");

	private static final String WIN_MSG = String.format("Player wins!%n");

	private static final String DO_EXIT_MSG = "Player leaves the game"; 
	
	private static final String GAME_OVER_MSG = "[GAME OVER] "; 
	
	public String newLine; 

	protected Game game;
	

	public GamePrinter(Game game) {
		this.game = game;
		

		margin = StringUtils.repeat(SPACE, MARGIN_SIZE);

		String roadBorder = ROAD_BORDER_PATTERN + StringUtils.repeat(ROAD_BORDER_PATTERN, (CELL_SIZE + 1) *  game.getVisibility());
		indentedRoadBorder = String.format("%n%s%s%n", margin, roadBorder);

		String laneDelimiter = StringUtils.repeat(LANE_DELIMITER_PATTERN, CELL_SIZE);
		String lanesSeparator = SPACE + StringUtils.repeat(laneDelimiter + SPACE,  game.getVisibility() - 1) + laneDelimiter + SPACE;

		indentedLlanesSeparator = String.format("%n%s%s%n", margin, lanesSeparator);
		newLine =  System.getProperty("line.separator");
	}
	


	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();

		// Game Status
		
		str.append(game.getInfo());
		
		// Paint game

		str.append(indentedRoadBorder);

		String verticalDelimiter = SPACE;

		for (int y = 0; y < game.getRoadWidth(); y++) {
			str.append(this.margin).append(verticalDelimiter);
			for (int x = game.getPlayerX(); x < game.getVisibility() + game.getPlayerX(); x++) {
				str.append(StringUtils.centre(game.positionToString(x, y), CELL_SIZE))
						.append(verticalDelimiter);
			}
			if (y <  game.getRoadWidth() - 1) {
				str.append(this.indentedLlanesSeparator);
			}
		}
		str.append(this.indentedRoadBorder);
		
		return str.toString();
	}

	
	public String endMessage() {

		StringBuilder s = new StringBuilder();
		s.append(GAME_OVER_MSG);
		int x = game.getGameOver();
		switch (x) {
			case 1: {
				s.append(CRASH_MSG);
				break;
			}
			case 3: {
				s.append(WIN_MSG);
				break;
			}
			case 2: {
				s.append(DO_EXIT_MSG);
				break;
			}
		}
		return s.toString();
	}

}
