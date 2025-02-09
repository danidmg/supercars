package game_objects;

import logic.Game;

public class Obstacle extends GameElement {
	
	private static int obstacle_counter = 0;
	protected int lives;
	public Obstacle(Game game, int positionX, int positionY) {
		super(game,positionX,positionY);
		this.lives = 1;
		this.symbol = "░";
	}
	
	public void onEnter() {
		obstacle_counter++;	
	}
	
	public void onDelete() {
		obstacle_counter--;
	}
	
	public static void reset() {
		obstacle_counter = 0;
	}

	public static int getObstacle_counter() {
		return obstacle_counter;
	}
	
	public boolean isAlive() {
		if ((this.positionX < game.getPlayerX()) || (this.lives == 0)) {
			return false;
		} 
		else {
			return true;
		}
	}
	
	public boolean receiveCollision(Player player) {
		player.deadSymbol();
		game.gameOverLost();
		onDelete();
		return true;
	}
	
	@Override
	public boolean receiveShot() {
		this.lives--;
		return true;
	}
}