package game_objects;

import logic.Game;

public class Coin extends GameElement {
	
	private static int coin_counter = 0;
	public Coin(Game game, int positionX, int positionY) { // Constructor de Coin
		super(game,positionX,positionY); // Llama al constructor grande
		this.symbol = "¢";
	}

	public void onEnter() {
		coin_counter++;
	}

	public void onDelete() {
		coin_counter--;
	}

	public static void reset() {
		coin_counter = 0;
	}

	public static int getCoin_counter() {
		return coin_counter;
	}
	
	public boolean receiveCollision(Player player) {
		player.addCoin(1);
		this.positionX = -1; // we send it out of the game so if the player is static doesn´t take more than 1
		return true;
	}

}