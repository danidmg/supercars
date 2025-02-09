package logic;

import game_objects.Collider;

public class ExplodeAction implements InstantAction{

	private int x;
	private int y;
	
	public ExplodeAction(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void execute(Game game) {
		for(int i = x-1; i <= x+1; i++) {
			for(int j = y-1; j <= y+1; j++) {
				Collider ge = game.getObjectInPosition(i,j);
				if (ge != null) {
					ge.receiveExplosion();
				}
			}
		}
	}
}