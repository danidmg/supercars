package game_objects;

import logic.ExplodeAction;
import logic.Game;

public class Grenade extends GameElement {

	private int counter;
	public Grenade(Game game, int positionX, int positionY) {
		super(game, positionX, positionY);
		this.counter = 3;
		this.symbol = "ð[" + this.counter + "]";
	}

    @Override 
	public void update() {
    	this.counter--;
		if (this.counter > 0) {
			this.symbol = "ð[" + this.counter + "]";
		}
		else {
			this.symbol = "";
			game.actionExecute(new ExplodeAction(this.positionX,this.positionY));
		}
			
	}
		
	@Override
	public void onDelete() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append(super.toString());
		str.append(" ");
		str.append(this.counter);
		
		return str.toString();
	}

	@Override
	public boolean receiveCollision(Player player) {
		return false;
	}

}
