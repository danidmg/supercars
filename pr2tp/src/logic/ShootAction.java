package logic;

import game_objects.Collider;
import game_objects.GameElement;

public class ShootAction implements InstantAction{

	@Override
	public void execute(Game game) {
		for (int x = game.getPlayerX() + 1; x <= game.getPlayerX() + game.getVisibility() - 1; x++) {
			Collider ge = game.getObjectInPosition(x, game.getPlayerY());
			if (ge != null) {
				if (ge.receiveShot()) {
					game.updateGameElements();
					break;
				}
			}
		}
		game.setCycle(game.getCycle() + 1);
	}
}