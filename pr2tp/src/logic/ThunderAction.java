package logic;

import game_objects.Collider;

public class ThunderAction implements InstantAction {

	@Override
	public void execute(Game game) {
		int x = game.getRandomDistance();
		int y = game.getRandomLane();
		
		Collider ge = game.getObjectInPosition(x+ game.getPlayerX(),y);
		
		System.out.println("Thunder ⚡  hit position: (" + x + "," + y + ")") ;
		
		if (ge != null) {	
			System.out.println("⚡⚡⚡Object hit!⚡⚡⚡") ;
			if (ge.receiveThunder()) {
				game.updateGameElements();
			}
		}
	}

}
