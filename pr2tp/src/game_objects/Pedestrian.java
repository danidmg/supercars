package game_objects;


import logic.Game;

public class Pedestrian extends Obstacle {
	
	private int direction = game.getRandom().nextInt(2); // random to decide if the player goes up or down
	
	public Pedestrian(Game game, int positionX, int positionY) {
		super(game, positionX, positionY);
		this.symbol = "☺";
		this.lives = 1;
	}
	@Override 
	public boolean receiveShot() {
		this.lives--;
		game.pedestrianShot(); // pone las monedas a 0
		return true;
		}
	
	public void movePedestrian() {
		//this.positionX = this.positionX + 1; // el pedestrian se mueve recto
		
		if (this.positionY == game.getRoadWidth() -1 ) {
			direction = 1;
		}
		else if (this.positionY == 0) {
			direction = 0;
		}
		if (direction == 1) {
			this.positionY = this.positionY - 1;//moveup
		}
		else {
			this.positionY = this.positionY + 1;//movedown
		}
	
	}
		
	public void update() {
		movePedestrian();
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(super.toString());
		if (direction == 1) {
			str.append(" up");
		}
		else {
			str.append(" down");
		}
		return str.toString();
	}
}
	