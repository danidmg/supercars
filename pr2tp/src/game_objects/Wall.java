package game_objects;

import logic.Game;

public class Wall extends Obstacle{
	
	public Wall(Game game, int positionX, int positionY) {
		super(game,positionX,positionY);
		this.lives = 3;
		this.symbol = "█";
	}
	
	public void update() {
		if (this.lives == 3) {
			this.symbol =  "█";
		}
		else if (this.lives == 2) {
			this.symbol =  "▒";
		}
		else if (this.lives == 1) {
			this.symbol =  "░";
		}
		else if(this.lives == 0) {
			game.wallDestroyed();
		}
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(super.toString());
		if (lives == 3) {
			str.append(" 3");
		}
		else if (lives == 2) {
			str.append(" 2");
		}
		else {
			str.append(" 1");
		}
		return str.toString();
	}
}
