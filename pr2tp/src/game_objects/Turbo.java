package game_objects;

import logic.Game;

public class Turbo extends GameElement{
	public Turbo(Game game, int positionX, int positionY) {
		super(game,positionX,positionY);
		this.symbol = ">>>";
	}

	@Override
	public boolean receiveCollision(Player player) {
		player.setPositionX(player.getPositionX()+3);
		game.playerHit();
		return false;
	}
}
