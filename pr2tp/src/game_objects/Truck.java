package game_objects;

import logic.Game;

public class Truck extends Obstacle{
	
	public Truck(Game game, int positionX, int positionY) {
		super(game, positionX, positionY);
		this.symbol = "←";
		this.lives = 1;
	}

	public void moveTruck() {
		this.positionX = this.positionX - 1; // truck moves left
	}
	
	public void update() {
		if(this.isAlive()) {
			moveTruck();
		}
	}
}
