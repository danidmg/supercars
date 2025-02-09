package game_objects;

import logic.Game;

public class Player extends GameElement {
	private int coinsW;

	public Player(Game game, int positionX, int positionY) {
		super(game, positionX, positionY);
		this.coinsW = 0;
		this.symbol = ">";
	}

	public int getCoinsW() {
		return coinsW;
	}
	
	public void resetCoinsW() {
		this.coinsW = 0;
	}

	public void addCoin(int n) {
		this.coinsW  = this.coinsW + n;
	}

	public void moveUp() {
		this.positionY--;	
	}
	
	public void moveDown() {
		this.positionY++;	
	}
	
	public void moveForwards() {
		this.positionX++;
	}

	public boolean isAlive() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean doCollision() {
		Collider other = game.getObjectInPosition(this.positionX, this.positionY);
		if (other != null) {
			return other.receiveCollision (this);
		}
		return false;
	}


	public void deadSymbol() {
		this.symbol = "@";
	}
	
	public void addSuperCoin() {
		this.coinsW = this.coinsW + 1000;
	}

	@Override
	public boolean receiveCollision(Player player) {
		return false;
	}
}
