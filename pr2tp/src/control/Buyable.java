package control;

import exceptions.NotEnoughCoinsException;
import logic.Game;

public interface Buyable {
	
	public int cost();

	public default void buy(Game game) throws NotEnoughCoinsException{
		if ((game.getPlayerCoins() - cost()) >= 0) {
			game.getPlayer().addCoin(-cost());
		}
		else { 
			throw new NotEnoughCoinsException();
		}
	}
	
}
