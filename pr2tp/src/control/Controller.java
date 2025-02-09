package control;

import java.util.Scanner;

import commands.Command;
import exceptions.GameException;
import exceptions.InputOutputRecordException;
import logic.Game;
import view.GamePrinter;

public class Controller {

	private static final String PROMPT = "Command > ";

	private Game game;
	private Scanner scanner;
	private GamePrinter printer;

	public Controller(Game game, Scanner scanner) {
		this.game = game;
		this.scanner = scanner;
		this.printer = new GamePrinter(game);
	}

	public void printGame() {
		System.out.println(printer);
	}
	
	public void printEndMessage() {
		System.out.println(printer.endMessage());
	}	

	public void run() {
		game.initializeGame();
		try {
			game.recordShow();
		}
		catch(InputOutputRecordException e) {
			System.out.println(e.getMessage());
		}
		boolean refreshDisplay = true;
		printGame();
		while (!game.isGameOver()){	
			System.out.println(PROMPT);
			String s = scanner.nextLine();
			String[] parameters = s.toLowerCase().trim().split(" ");
			System.out.println("[DEBUG] Executing: " + s);
			try {
				Command command = Command.getCommand(parameters);
				refreshDisplay = command.execute(game);
			}
			catch (GameException ex) {
				System.out.format("[ERROR]: %s %n %n", ex.getMessage());
			}
			if (refreshDisplay) 
				printGame();
		}
		printEndMessage();
		try {
			game.overWriteRecord();
		}
		catch(InputOutputRecordException e) {
			System.out.println(e.getMessage());
		}
	}

}
