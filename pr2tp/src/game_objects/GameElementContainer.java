package game_objects;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameElementContainer {
	private List<GameElement> gameElements;
	
	public GameElementContainer() {
		gameElements = new ArrayList<>();
	}
	
	public void addElement(GameElement ge) {
		this.gameElements.add(ge);
	}
	
	public void updateElements() {
		for (GameElement g: this.gameElements) {
			g.update();
		}
	}
	
	public void removeDeadElement() { // Mira los objetos que están muertos y los borra
		
		for (Iterator<GameElement> iterator = gameElements.iterator(); iterator.hasNext();) {
		    GameElement g = iterator.next();
		    if(g.isAlive() == false) {
		    	g.onDelete();
		        iterator.remove();
		    }
		}
		/* This one throws an exception
		for (GameElement g: this.gameElements) {
			if(g.isAlive() == false) {
				g.onDelete();
				this.gameElements.remove(g);
			}
		}*/
	}
	
	public boolean positionOccupied(GameElement ge) { //checks if a position is already occupied
		for (GameElement g: this.gameElements) {
			if (g.isInPosition(ge.positionX, ge.positionY)){
				return true;
			}
		}
		return false;
	}
	
	public Collider getObjectInPosition(int x,int y) {
		for (GameElement g: this.gameElements) {
			if (g.isInPosition(x, y)){
				return g;
			}
		}
		return null;
	}
	
	public String whatSymbol(int x, int y) {
		for (GameElement g: this.gameElements) {
			if (g.isInPosition(x, y)) {
				return g.getSymbol();
			}
		}	
		return "";
	}
	
	public int totalObstacles(){
		int total = Obstacle.getObstacle_counter();
		return total;
	}
	
	public int totalCoins(){
		int total = Coin.getCoin_counter();
		return total;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (GameElement g: this.gameElements) {
			s.append(g);
			s.append("\n");
		}
		return s.toString();
	}

}
