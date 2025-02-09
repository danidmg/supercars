package game_objects;

public interface Collider {
	boolean doCollision();
	boolean receiveCollision(Player player);
	boolean receiveShot();
	boolean receiveExplosion();
	boolean receiveThunder();
	boolean recieveWave();
}