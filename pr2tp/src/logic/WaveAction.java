package logic;

import game_objects.Collider;
import game_objects.GameElement;

public class WaveAction implements InstantAction{

	@Override
	public void execute(Game game) {
		for (int x = game.getPlayerX() + game.getVisibility() - 1; x >= game.getPlayerX(); x--) {
			for (int y = 0; y < game.getRoadWidth(); y++) {
				Collider ge = game.getObjectInPosition(x, y);
				if (ge != null) {
					Collider ge2 = game.getObjectInPosition(x + 1, y);
					if (ge2 == null) { //si no hay nada en la posicion a la que lo quiero mover
						ge.recieveWave();
					}
				}
			}
		}
		game.setCycle(game.getCycle() + 1);
	}

}
