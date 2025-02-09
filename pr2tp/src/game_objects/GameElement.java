package game_objects;

import logic.Game;

public abstract class GameElement implements Collider{
	protected int positionX, positionY;
	protected Game game;
	protected String symbol;
	
	public GameElement(Game game, int positionX, int positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.game = game;
	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}
	
	public String getSymbol() { 
		return this.symbol; 
	}
		
	public boolean isInPosition(int x, int y) {
		return this.positionX == x && this.positionY == y;
	}
	
	public void onEnter() {
	}
	
	public void update() {
	}
	
	public void onDelete() {
	}
	
	public boolean isAlive() {
		if (this.positionX < game.getPlayerX()) {
			return false;
		} 
		else {
			return true;
		}
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(this.symbol);
		str.append(" (");
		str.append(this.positionX);
		str.append(", ");
		str.append(this.positionY);
		str.append(")");
		
		return str.toString();
	}
	
	public static boolean superCoinAlive() {
		return SuperCoin.getSUPERCOINEXISTS();
	}
	
	public boolean receiveShot() {
		return false;
	}
	
	public boolean receiveExplosion() {
		return receiveShot();
	}
	
	public boolean receiveThunder() {
		this.positionX = - 1; // we send it out of the game so it dies
		return true;
	}
	
	public boolean doCollision() {
		return false;
	}
	
	@Override
	public boolean recieveWave() {
		positionX = positionX + 1;
		return false;
	}
}