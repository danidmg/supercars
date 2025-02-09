package game_objects;

import logic.Game;

public class SuperCoin extends GameElement {
	
	private static boolean SUPERCOIN_EXISTS = false;
	
	public SuperCoin(Game game, int positionX, int positionY) { 
		super(game,positionX,positionY);
		this.symbol = "$";
	}

	@Override
	public boolean receiveCollision(Player player) {
		game.superCoinCatched();
		this.positionX = -1; // we send it out of the game so if the player is static doesn´t take more than 1
		SUPERCOIN_EXISTS = false;
		return true;
	}

	@Override
	public void onEnter() {
		SUPERCOIN_EXISTS = true;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDelete() {
		// TODO Auto-generated method stub
		
	}
	
	public static boolean getSUPERCOINEXISTS() {
		return SUPERCOIN_EXISTS;
	}
	
}
