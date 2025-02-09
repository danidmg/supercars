package logic;

import java.io.*; //habra que cambiarlo en algun momento
import java.util.Random;

import control.Level;
import exceptions.CommandExecuteException;
import exceptions.InputOutputRecordException;
import exceptions.InvalidPositionException;
import exceptions.PlayerOutException;
import game_objects.Collider;
import game_objects.GameElement;
import game_objects.GameElementContainer;
import game_objects.GameElementGenerator;
import game_objects.Player;
import game_objects.Grenade;
import view.GameSerializer;

public class Game {
	
	private static final int GAME_OVER_NOT_ENDED = 0;
	private static final int GAME_OVER_ENDED_CRASH = 1;
	private static final int GAME_OVER_ENDED_EXIT = 2;
	private static final int GAME_OVER_ENDED_WIN = 3;
	
	private static final String INFO = "Elements of the game:\n" + 
		"[Car] the racing car\n" + 
		"[Coin] gives 1 coin to the player\n" + 
		"[Obstacle] hits car\n" + 
		"[GRENADE] Explodes in 3 cycles, harming everyone around\n"+
		"[WALL] hard obstacle\n" +   
		"[TURBO] pushes the car: 3 columns\n"+
		"[SUPERCOIN] gives 1000 coins\n"+
		"[TRUCK] moves towards the player\n"+
		"[PEDESTRIAN] person crossing the road up and down\n\n";
	
	private Level level;        // Level parameters
	private Long seed;        // Level generation
	private Random random;
	private boolean isTestMode;    // Debugging ease
	
	private Player player;
	private int cycle; //nos dice cuanto hemos avanzado en la partida
	private long time; //nos dice cuando se ha inicializado la partida
	private int gameOver; //si el juego aun no se ha acabado es 0, si se ha acabado por choque es 1, 
						  //si se ha acabado por exit es 2 y si se ha acabado por win es 3
	private GameElementContainer container;
	private GameSerializer serializer;
	
	public Game(Long seed, Level level, boolean isTestMode) {
	    // Initialize variables
		this.seed = seed;
	    this.random = new Random(seed);
	    this.level = level;
	    this.isTestMode = isTestMode;
	    
	    this.player = new Player(this, 0, level.getWidth()/2);
	    this.cycle = 0;
	    this.time = System.currentTimeMillis(); 
	    this.gameOver = GAME_OVER_NOT_ENDED;
	    this.container = new GameElementContainer();
	    this.serializer = new GameSerializer(this);
	}

	public int getVisibility() {
		return level.getVisibility();
	}

	public int getRoadWidth() {
		return level.getWidth();
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public int getPlayerY() {
		return player.getPositionY();
	}
	
	public int getPlayerX() {
		return player.getPositionX();
	}
	
	public int getPlayerCoins() {
		return player.getCoinsW();
	}
	
	public Random getRandom() {
		return random;
	}
	
	public static int getGameOverEndedCrash() {
		return GAME_OVER_ENDED_CRASH;
	}

	public static int getGameOverEndedWin() {
		return GAME_OVER_ENDED_WIN;
	}

	public static int getGameOverEndedExit() {
		return GAME_OVER_ENDED_EXIT;
	}

	public Level getLevel() {
		return this.level;
	}
	
	public int getLength() {
		return level.getLength();
	}
	
	public double getCoinFrequence() {
		return level.getCoinFrequency();
	}
	
	public double getObstacleFrequence() {
		return level.getObstacleFrequency();
	}
	
	public int getCycle() {
		return cycle;
	}
	
	public void addCycle() {
		this.cycle++;
		generateRuntimeObjects(); //throws a thunder
		updateGameElements();	
	}
	
	public void generateRuntimeObjects() {
		// Note we use this method to create and inject new objects or actions on runtime.
		if (this.getLevel().hasAdvancedObjects()) {
			this.actionExecute(new ThunderAction());
		}
	}
	
	public long getTime() {
		return time;
	}
	
	public int getRandomLane() {
		return random.nextInt(getRoadWidth());
	}
	
	public int getRandomDistance() {
		return random.nextInt(getVisibility());	
	}

	public String positionToString(int x, int y) {
		String symbol = "";
		
		if (x == player.getPositionX() && y == player.getPositionY()) {
			symbol = player.getSymbol();
		}
		else if(x == getLength()) { 
			symbol = "¦";
		}
		else { 
			symbol = container.whatSymbol(x, y);
		}	
		return symbol;
	}

	public void movePlayerUp() {
		player.moveUp();
	}
	
	public void movePlayerDown() {
		player.moveDown();
	}
	
	public void playerHit(){
		player.doCollision();
	}
	
	public void gameOverLost() {
		this.gameOver = GAME_OVER_ENDED_CRASH;
	}
	
	public void gameOverWon() {
		this.gameOver = GAME_OVER_ENDED_WIN;
	}
	
	public void gameOverExit() {
		this.gameOver = GAME_OVER_ENDED_EXIT;
	}
	
	public boolean isGameOver() {
		boolean over = false;
		if (this.gameOver > 0)
			over = true;
		return over;
	}
	
	public int getGameOver() { 
		return this.gameOver;
	}
	
	public String getInfo() {
		StringBuilder str = new StringBuilder();
		
		str.append("\n");
		str.append("Distance: ");
		str.append(getLength() - getPlayerX());
		str.append("\n");
		str.append("Coins: ");
		str.append(player.getCoinsW());
		str.append("\n");
		str.append("Cycle: ");
		str.append(cycle);
		str.append("\n");
		str.append("Total Obstacles: ");
		str.append(this.container.totalObstacles()); 
		str.append("\n");
		str.append("Total Coins: ");
		str.append(this.container.totalCoins());
		str.append("\n");
		if (GameElement.superCoinAlive()) {
			str.append("Supercoin is present");
		}
		str.append("\n");
		if (!isTestMode) {
			str.append("Ellapsed Time: ");
			double t = (System.currentTimeMillis() - time)/1000.0;
			str.append(t);
			str.append(" s\n");
		}
		return str.toString();
	}

	public void actionExecute (InstantAction instantAction) {
		instantAction.execute(this);
	}
	
	public void win() {
		if (player.getPositionX() == getLength()+1){
			gameOverWon();
		}
	}

	public void activateTestMode() {
		this.isTestMode = true;
	}
	
	public void initializeGame() {
		GameElementGenerator.generateGameElements(this,level);
	}

	public void cInfo(){
		System.out.print(INFO);
	}

	public void cMoveDown() throws PlayerOutException{
		if (getPlayerY() != getRoadWidth()-1) {
			movePlayerDown();
			player.moveForwards();
			playerHit();
			addCycle();
			playerHit();
			win();
		}
		else
			throw new PlayerOutException();
	}

	public void cMoveUp() throws PlayerOutException{
		if (getPlayerY() != 0) {
			movePlayerUp(); 
			player.moveForwards();
			playerHit();
			addCycle();
			playerHit();
			win();
		}
		else 
			throw new PlayerOutException();
	}

	public void cMoveStraight() {
		player.moveForwards();
		playerHit();
		addCycle();
		playerHit();
		win();
	}
	
	public void cReset(String newLevel, Long newSeed) throws CommandExecuteException{
	    if (Level.valueOfIgnoreCase(newLevel) == null) {
	    	throw new CommandExecuteException("Level name not valid");
	    }
	    else {
	    	this.level = Level.valueOfIgnoreCase(newLevel);
	    }
	    this.seed = newSeed;
	    this.random = new Random(seed);
		player.setPositionY(level.getWidth()/2);
		player.setPositionX(0);
		player.resetCoinsW();
		this.cycle = 0;
		this.time = System.currentTimeMillis();
		this.random = new Random(seed);
		GameElementGenerator.reset(level);
		this.container = new GameElementContainer();
		initializeGame();
		try {
			recordShow();
		} catch (InputOutputRecordException e) {
			e.getMessage();
		}
	}
	
	public void cTest() {
		activateTestMode();
	}

	public void cExit() {
		gameOverExit();	
	}
	
	public void tryToAddObject(GameElement o, double frequency) {
		 if (random.nextDouble() < frequency) {
			 if (!container.positionOccupied(o)) { 
				 container.addElement(o);
				 o.onEnter();
			 }
		 }
	}
	
	public Collider getObjectInPosition(int x, int y) {
		return container.getObjectInPosition(x, y);
	}

	public void cGrenade(int x, int y) throws InvalidPositionException{ 
		updateGameElements();
		
		if (x > level.getVisibility() || y > level.getWidth()-1 ) { //In case grenade placed too far
			throw new InvalidPositionException("That is not a valid position for the grenade");
		}
		x = player.getPositionX() + x; 
		
		if (container.getObjectInPosition(x, y) == null) {
			container.addElement(new Grenade(this,x,y));
		}
		this.cycle++;
	}

	public void cClear() {
		GameElementGenerator.reset(this.level);
		this.container = new GameElementContainer();
	}

	public void cCheat(String advancedGe) throws CommandExecuteException{ //this implementation is weird, we have to do the following in order: 1.Check the parameter is advanced game element object 2.Delete last row 3.Add new game element
		//1
		if (advancedGe.equals("wall") || advancedGe.equals("turbo") || advancedGe.equals("super") || advancedGe.equals("truck") || advancedGe.equals("ped")) {
		}
		else {
			throw new CommandExecuteException("Not a valid advanced game element");
		}
		//2
		int x = this.getVisibility() + player.getPositionX() - 1;
		for (int y = 0; y < this.getRoadWidth(); y++) {
			Collider ge = container.getObjectInPosition(x,y);
			if (ge != null) {
				ge.receiveThunder();
			}
		}
		updateGameElements();
		
		//3
		GameElementGenerator.addAdvancedGe(advancedGe, x, getRandomLane(), this);
	}
	
	public void updateGameElements() { 
		container.updateElements();
		container.removeDeadElement();
	}
	
	public void pedestrianShot() {
		player.resetCoinsW();
	}
	
	public void wallDestroyed() {
		player.addCoin(5);
	}
	
	public void superCoinCatched() {
		player.addCoin(1000);
	}
	
	public void gameElementDelete () {
		container.removeDeadElement();
	}

	public void cSerialize() {
		System.out.println(serializer);
	}

	public void cSave(String file) throws IOException{
		file = file + ".txt";
		try(FileWriter write = new FileWriter(file); 
			BufferedWriter bufferedWrite = new BufferedWriter(write)){
			String data = serializer.toString();
			bufferedWrite.write(data);
		}
	}

	public void cDump(String file) throws IOException{
		file = file + ".txt";
		try(FileReader read = new FileReader(file); 
			BufferedReader bufferedRead = new BufferedReader(read)){
			String data; 
			data = bufferedRead.readLine();
			while(data != null){
				System.out.println(data);
				data = bufferedRead.readLine();
			}
		}
	}

	public String getSerializeInfo() {
		StringBuilder str = new StringBuilder();
		
		str.append("Level: ");
		str.append(level.getName());  
		str.append("\n");
		str.append("Cycles: ");
		str.append(cycle);
		str.append("\n");
		str.append("Coins: ");
		str.append(player.getCoinsW());
		str.append("\n");
		str.append("Ellapsed Time: ");
		double t = (System.currentTimeMillis() - time)/1000.0;
		str.append(t);
		str.append(" s\n");
		str.append("Game Objects: \n");
		str.append(player.toString());
		str.append("\n");
		str.append(container);
		
		return str.toString();
	}

	public void setCycle(int cycle) {
		this.cycle = cycle;
	}

	public void recordShow() throws InputOutputRecordException{
		try {
			Record.readRecord(this); //loads the file
		}
		catch(FileNotFoundException e) {
			System.out.println("Record file not found, creating file...");
			try {
				Record.createFile();
			}
			catch (IOException p) {
				System.out.println("IO except");
				throw new InputOutputRecordException("Error with the files");
			}
			throw new InputOutputRecordException("Error with the files");
		} 
		catch (IOException e) {
			System.out.println("IO except");
			throw new InputOutputRecordException("Error with the files");
		}
		
		Integer rec = Record.giveRecord(level.getName());
		
		if (rec == 0) {
			System.out.println("Record not set yet");
		}
		else {
			System.out.print("Record: ");
			System.out.print(rec/1000.0);
			System.out.println("s");
		}
	}

	public void overWriteRecord() throws InputOutputRecordException {
		if (gameOver == GAME_OVER_ENDED_WIN) { //only executes when the game ended in a victory
			if ((System.currentTimeMillis() - time) < Record.giveRecord(level.getName()) || Record.giveRecord(level.getName()) == 0) {
				System.out.print("NEW RECORD!!: ");
				System.out.print((System.currentTimeMillis() - time)/1000.0);
				System.out.println("s");
				Record.overWriteRecord(System.currentTimeMillis() - time, level.getName());
				try {
					Record.writeRecord(this);
				}
				catch(IOException e) {
					System.out.println("IO except");
					throw new InputOutputRecordException("Error with the files");
				}
			}
		}
	}
}