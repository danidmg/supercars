package game_objects;

import control.Level;
import logic.Game;


public class GameElementGenerator {
	
	public static void generateGameElements(Game game, Level level) {
		for(int x = game.getVisibility() /2; x < game.getLength(); x ++) {
			game.tryToAddObject(new Obstacle(game, x, game.getRandomLane()),level.getObstacleFrequency());
			game.tryToAddObject(new Coin(game, x, game.getRandomLane()),level.getCoinFrequency());
			if (level.hasAdvancedObjects()) {
				game.tryToAddObject(new Wall(game, x, game.getRandomLane()), level.getAdvancedObjectFrequency());
				game.tryToAddObject(new Turbo(game, x, game.getRandomLane()), level.getAdvancedObjectFrequency());
				if (!SuperCoin.superCoinAlive()) {
					game.tryToAddObject(new SuperCoin(game, x, game.getRandomLane()),level.getAdvancedObjectFrequency());
				}
				game.tryToAddObject(new Truck(game, x, game.getRandomLane()), level.getAdvancedObjectFrequency());
				game.tryToAddObject(new Pedestrian(game, x, 0), level.getAdvancedObjectFrequency());
			}
		}
	}
	
	public static void reset(Level level){
		Obstacle.reset();
		Coin.reset();
	}
	
	public static void addAdvancedGe(String advancedGe, int x, int y, Game game){
		if (advancedGe.equals("wall")) {
			game.tryToAddObject(new Wall(game,x,y), 1);
		}
		else if (advancedGe.equals("turbo")) {
			game.tryToAddObject(new Turbo(game,x,y), 1);
		}
		else if (advancedGe.equals("super")) {
			game.tryToAddObject(new SuperCoin(game,x,y), 1);
		}
		else if (advancedGe.equals("truck")) {
			game.tryToAddObject(new Truck(game,x,y) ,1);
		}
		else {
			game.tryToAddObject(new Pedestrian(game,x,y), 1);
		}
	}
}

